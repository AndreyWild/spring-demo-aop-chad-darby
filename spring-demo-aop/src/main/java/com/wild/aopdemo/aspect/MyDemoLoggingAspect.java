package com.wild.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {

    // this is where we add all of our related advices for loggingx

    // let's start with an @Before advice
    // @Before(value = "execution(public void addAccount())")
    // @Before(value = "execution(public void com.wild.aopdemo.dao.AccountDAO.addAccount())")
    // @Before(value = "execution(public void add*())")
    // @Before(value = "execution(void add*())")
    // @Before(value = "execution(* add*())")
    // @Before(value = "execution(* add*(com.wild.aopdemo.entity.Account))")
    // @Before(value = "execution(* add*(com.wild.aopdemo.entity.Account, ..))")
    // @Before(value = "execution(* add*(..))")

    @Before(value = "execution(* com.wild.aopdemo.dao.*.*(..))") // 1* - eny Return type, 2* eny Class, 3* eny Method
    public void beforeAddAccountAdvice() {
        System.out.println("\n=====>>> Executing @Before advice on method");
    }
}
