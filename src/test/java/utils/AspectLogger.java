package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
public class AspectLogger {
    public static Logger LOG = LogManager.getLogger();

    @Pointcut("execution(* utils.DBmethods.*(..))")
    public void selectAllBDmethods() {
    }

    @Before(value = "selectAllBDmethods()")
    public void beforeDBMethods(JoinPoint jp) {
        LOG.info("DB method "+ jp.getSignature().getName() + " has been called with args:");
        for(int i =0; i<jp.getArgs().length; i++) {
            LOG.info("type: " + jp.getArgs()[i].getClass().getSimpleName() +", value: "+ jp.getArgs()[i].toString());
        }
    }

    @AfterReturning(value = "selectAllBDmethods()", returning= "result")
    public  void afterDBMethods(JoinPoint jp, Object result){
        LOG.info("DB method "+ jp.getSignature().getName() + " return: " );
        LOG.info("type: " + result.getClass().getSimpleName() + ", value: " + result);
    }


    @AfterThrowing(value = "selectAllBDmethods()", throwing = "ex")
    public void afterThrowing(JoinPoint jp, Throwable ex){
        LOG.error(jp.getClass() + "." + jp.getSignature().getName()+" method has thrown exception:\n"+ ex.getStackTrace());
    }



}



