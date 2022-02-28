package com.wild.springdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class CRMLoggingAspect {

    // setup logger
    private Logger myLogger = Logger.getLogger(CRMLoggingAspect.class.getName());

    // setup pointcut declarations
    @Pointcut(value = "execution(* com.wild.springdemo.controller.*.*(..))")
    public void forControllerPackage() {
    }

    // do the same for service and dao
    @Pointcut(value = "execution(* com.wild.springdemo.service.*.*(..))")
    public void forServicePackage() {
    }

    @Pointcut(value = "execution(* com.wild.springdemo.dao.*.*(..))")
    public void forDaoPackage() {
    }

    @Pointcut(value = "forControllerPackage() || forServicePackage() || forDaoPackage()")
    private void forAppFlow() {
    }

    // add @Before advice
    @Before(value = "forAppFlow()")
    public void before(JoinPoint theJoinPoint) {

        // display method we are calling
        String theMethod = theJoinPoint.getSignature().toShortString();
        myLogger.info("\n=====>>> in @Before calling method: " + theMethod);

        // display the arguments to the method

        // get the arguments
        Object[] args = theJoinPoint.getArgs();

        // loop thru and display args
        for (Object tempArg : args) {
            myLogger.info("\n=====>> argument: " + tempArg);
        }
    }

    // add @AfterReturning advice
    @AfterReturning(
            pointcut = "forAppFlow()",
            returning = "theResult")
    public void afterReturning(JoinPoint theJoinPoint, Object theResult) {

        // display method we are returning from
        String theMethod = theJoinPoint.getSignature().toShortString();
        myLogger.info("\n=====>>> in @AfterReturning from method: " + theMethod);

        // display data returned
        myLogger.info("\n=====>>> result: " + theResult);
    }
}