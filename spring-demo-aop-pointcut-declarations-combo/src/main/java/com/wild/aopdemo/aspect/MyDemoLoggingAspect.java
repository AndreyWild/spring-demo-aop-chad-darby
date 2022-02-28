package com.wild.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {

    @Pointcut("execution(* com.wild.aopdemo.dao.*.*(..))")
    private void forDaoPackage() {
    }

    // create pointcut for getter method
    @Pointcut(value = "execution(* com.wild.aopdemo.dao.*.get*(..))")
    private void getter() {
    }

    // create pointcut for setter method
    @Pointcut(value = "execution(* com.wild.aopdemo.dao.*.set*(..))")
    private void setter() {
    }

    // combine pointcut: include package ... exclude getter/setter
    @Pointcut("forDaoPackage() && !(getter() || setter())")
    private void forDaoPackageNoGetterSetter(){}

    @Before(value = "forDaoPackageNoGetterSetter()")
    public void beforeAddAccountAdvice() {
        System.out.println("\n=====>>> Executing @Before advice on method");
    }

    @Before(value = "forDaoPackageNoGetterSetter()")
    public void performApiAnalytics() {
        System.out.println("\n=====>>> Performing API analytics");
    }
}
