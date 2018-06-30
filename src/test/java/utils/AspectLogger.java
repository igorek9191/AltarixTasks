package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
public class AspectLogger {
    public static Logger LOG = LogManager.getLogger();


    @Pointcut("@annotation(org.testng.annotations.Test)")
    public void selectAllTests() {
    }

    //@Before(value = "selectAllTests()")
    public void before(JoinPoint jp) {
        LOG.info(jp.getTarget().getClass().getSimpleName() +"."+ jp.getSignature().getName() + " test is calling...");
    }

}



