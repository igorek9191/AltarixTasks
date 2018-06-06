
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.testng.annotations.Test;

@Aspect
public class AspectLogger {
    public static Logger LOG = LogManager.getLogger(AspectLogger.class);

    @Test
    public void test(){
        LOG.info("message from AspectLogger");
    }

    //@Pointcut("@annotation(org.testng.annotations.Test)")
    @Pointcut("execution(public * *(..))")
    public void selectAllTests() {
    }

    @Before(value = "selectAllTests()")
    public void before(JoinPoint jp) {
        LOG.info(jp.getSignature().getName() + "() method has been called ");
    }
}


    /*public void before(JoinPoint jp){
        StringBuffer logMessage = new StringBuffer();
        logMessage.append(jp.getTarget().getClass().getSimpleName());
        logMessage.append(".class, ");
        logMessage.append(jp.getSignature().getName());
        logMessage.append("() method has been called with argument(s): ");
        Object[] args = jp.getArgs();
        for (int i = 0; i < args.length; i++) {
            logMessage.append(args[i]).append(";");
        }
        LOG.info(logMessage.toString());
    }


    @AfterReturning(pointcut = "selectAllTests()", returning = "retVal")
    public void afterReturning( JoinPoint jp, Object retVal){
        LOG.info(jp.getSignature().getName() + "() method has returned value: " + retVal);
    }

    @AfterThrowing(pointcut = "selectAllTests()", throwing = "ex")
    public void afterThrowing(JoinPoint jp, Exception ex){
        LOG.error(jp.getTarget().getClass().getSimpleName()+".class, "+jp.getSignature().getName()+ "() method has throwed "+ex.getClass().getSimpleName()+", message: " + ex.getMessage());
    }
    */