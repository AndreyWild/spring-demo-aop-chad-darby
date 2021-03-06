package com.wild.aopdemo.aspect;

import com.wild.aopdemo.entity.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(value = 2)
public class MyDemoLoggingAspect {

    @Before(value = "com.wild.aopdemo.aspect.WildAopExpressions.forDaoPackageNoGetterSetter()")
    public void beforeAddAccountAdvice(JoinPoint theJoinPoint) {
        System.out.println("\n=====>>> Executing @Before advice on method");

        // display the method signature
        MethodSignature theMethodSignature = (MethodSignature) theJoinPoint.getSignature();

        System.out.println("Method: " + theMethodSignature);

        // display method arguments

        // get args
        Object[] args = theJoinPoint.getArgs();

        // loop thru args
        for (Object arg : args) {
            System.out.println(arg);

            if (arg instanceof Account) {

                // downcast and print Account specific stuff
                Account theAccount = (Account) arg;

                System.out.println("Account name: " + ((Account) theAccount).getName());
                System.out.println("Account level: " + ((Account) theAccount).getLevel());
            }
        }
    }
}
