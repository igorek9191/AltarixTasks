package utils;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.*;
import org.testng.annotations.Listeners;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.annotation.Annotation;
import java.util.List;

import static java.util.Arrays.asList;

public class LogTestListener implements ITestListener, IInvokedMethodListener {

    private static Logger LOG = LogManager.getLogger();

    // This belongs to ITestListener and will execute before starting of Test set/batch
    public void onStart(ITestContext arg0) {
        LOG.info("...:::NAGIBATOR_9000:::... started");
    }

    // This belongs to ITestListener and will execute, once the Test set/batch is finished
    public void onFinish(ITestContext arg0) {
        LOG.info("...:::NAGIBATOR_9000:::... finished");
    }

    // This belongs to ITestListener and will execute before the main resttest start (@Test)
    public void onTestStart(ITestResult arg0) {
        //if (shouldIntercept(arg0))
            LOG.info("Starting CLASS " + arg0.getInstanceName());
    }

    // This belongs to ITestListener and will execute only when the resttest is pass
    public void onTestSuccess(ITestResult arg0) {
        // This is calling the printTestResults method
        printTestResults(arg0);
    }

    // This belongs to ITestListener and will execute only on the event of fail resttest
    public void onTestFailure(ITestResult arg0) {
        // This is calling the printTestResults method
        printTestResults(arg0);
    }

    // This belongs to ITestListener and will execute only if any of the main resttest(@Test) get skipped
    public void onTestSkipped(ITestResult arg0) {
        printTestResults(arg0);
    }

    // This is just a piece of shit, ignore this
    public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
    }

    // This is the method which will be executed in case of resttest pass or fail
    // This will provide the information on the resttest
    private void printTestResults(ITestResult result) {

        /*if (result.getParameters().length != 0) {
            String params = null;
            for (Object parameter : result.getParameters()) {
                params += parameter.toString() + ",";
            }
            LOG.info("Test Method had the following parameters : " + params, true);
        }*/

        String status = null;

        switch (result.getStatus()) {
            case ITestResult.SUCCESS:
                status = "Pass";
                break;
            case ITestResult.FAILURE:
                status = "Failed";
                break;
            case ITestResult.SKIP:
                status = "Skipped";
        }
        if(status.equals("Pass")) LOG.info("Finished CLASS " +result.getInstanceName()+ ", status: " + status);
        else LOG.error("Finished CLASS " +result.getInstanceName()+ ", status: " + status);
    }

    // This belongs to IInvokedMethodListener and will execute before every method including @Before @After @Test
    public void beforeInvocation(IInvokedMethod method, ITestResult result) {
        if(method.getTestMethod().isBeforeClassConfiguration()) LOG.info("Before method of class " + result.getInstanceName());
        LOG.info("==> METHOD : " + returnMethodName(method.getTestMethod()));
    }

    // This belongs to IInvokedMethodListener and will execute after every method including @Before @After @Test
    public void afterInvocation(IInvokedMethod method, ITestResult result) {
        Throwable throwable = result.getThrowable();
        /*StringWriter sw = new StringWriter();
        PrintWriter writer = new PrintWriter(sw);
        throwable.printStackTrace(writer);*/

        if(result.getStatus() == 1) LOG.info("<== METHOD " + returnMethodName(method.getTestMethod()) + " finished successfully");
        if(result.getStatus() == 2) {
            LOG.error("<== METHOD " + returnMethodName(method.getTestMethod()) + " finished unsuccessfully");
            LOG.throwing(throwable);
        }
        if(result.getStatus() == 3) LOG.error("<== METHOD " + returnMethodName(method.getTestMethod()) + " was skipped");
        for(String s : result.getAttributeNames()) System.out.println(s);
    }

    // This will return method names to the calling function
    private String returnMethodName(ITestNGMethod method) {
        return method.getRealClass().getSimpleName() + "." + method.getMethodName();
    }

    // Три нижеследующих метода нужны для проверки,
    // аннотирован ли тестовый класс аннотацией ЭТОГО лисенера
    public boolean shouldIntercept(ITestResult result) {
        List<String> listeners = result.getTestContext().getCurrentXmlTest().getSuite().getListeners();
        return listeners.contains(this.getClass().getName()) || shouldIntercept(result.getTestClass().getRealClass(), this.getClass());
    }

    public boolean shouldIntercept(Class testClass, Class annotation) {
        Listeners listenersAnnotation = getListenersAnnotation(testClass);
        return listenersAnnotation != null && asList(listenersAnnotation.value()).contains(annotation);
    }

    private Listeners getListenersAnnotation(Class testClass) {
        Annotation annotation = testClass.getAnnotation(Listeners.class);
        return annotation != null ? (Listeners) annotation :
                testClass.getSuperclass() != null ? getListenersAnnotation(testClass.getSuperclass()) : null;
    }

}