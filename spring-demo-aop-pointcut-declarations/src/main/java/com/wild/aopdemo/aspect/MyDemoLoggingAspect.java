package com.wild.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {

    @Pointcut("execution(* com.wild.aopdemo.dao.*.*(..))")
    private void forDaoPackage(){}

    @Before(value = "forDaoPackage()")
    public void beforeAddAccountAdvice() {
        System.out.println("\n=====>>> Executing @Before advice on method");
    }

    @Before(value = "forDaoPackage()")
    public void performApiAnalytics(){
        System.out.println("\n=====>>> Performing API analytics");
    }
}
