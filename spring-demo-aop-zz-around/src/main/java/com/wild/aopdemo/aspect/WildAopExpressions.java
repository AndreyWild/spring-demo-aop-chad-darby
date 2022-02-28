package com.wild.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class WildAopExpressions {

    @Pointcut("execution(* com.wild.aopdemo.dao.*.*(..))")
    public void forDaoPackage() {
    }

    // create pointcut for getter method
    @Pointcut(value = "execution(* com.wild.aopdemo.dao.*.get*(..))")
    public void getter() {
    }

    // create pointcut for setter method
    @Pointcut(value = "execution(* com.wild.aopdemo.dao.*.set*(..))")
    public void setter() {
    }

    // combine pointcut: include package ... exclude getter/setter
    @Pointcut("forDaoPackage() && !(getter() || setter())")
    public void forDaoPackageNoGetterSetter(){}
}
