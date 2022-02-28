package com.wild.aopdemo.aspect;

import com.wild.aopdemo.entity.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(value = 5)
public class MyTestAspect {

    @AfterReturning(
            pointcut = "execution(* com.wild.aopdemo.dao.AccountDAO.getOneAccount(..))",
            returning = "result")
    public void afterReturningFindAccountsAdvice(JoinPoint theJoinPoint, Account result) {

        // print out which method we are advising on
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @AfterReturning on method: " + method);

        // print out the results of the method call
        System.out.println("\n=====>>> result is: " + result);

        // let's post-process the data ... let's modify it

        // convert the account name to uppercase
        ModifyAccountName(result);

        System.out.println("\n=====>>> result is: " + result);
    }

    private void ModifyAccountName(Account result) {
        result.setName(result.getName() + "_Wild");
    }
}
