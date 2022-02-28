package com.wild.aopdemo.aspect;

import com.wild.aopdemo.entity.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Logger;

@Aspect
@Component
@Order(value = 2)
public class MyDemoLoggingAspect {
    
    private static Logger myLogger = Logger.getLogger(MyDemoLoggingAspect.class.getName());

    @Around("execution(* com.wild.aopdemo.service.*.getFortune(..))")
    public Object aroundGrtFortune(ProceedingJoinPoint theProceedingJoinPoint) throws Throwable {

        // print out method we are advising on
        String method = theProceedingJoinPoint.getSignature().toShortString();
        myLogger.info("\n=====>>> Executing @Around on method: " + method);

        // get begin timestamp
        long begin = System.currentTimeMillis();

        // now, let's execute the method theProceedingJoinPoint(handle to target method) proceed(execute the target method)
        Object result = theProceedingJoinPoint.proceed();

        // get end timestamp
        long end = System.currentTimeMillis();

        // compute duration and display it
        long duration = end - begin;
        myLogger.info("\n=====>>> Duration: " + duration / 1000.0 + "seconds");

        return result;
    }

    @After("execution(* com.wild.aopdemo.dao.AccountDAO.findAccounts(..))")
    public void afterFinallyFindAccountAdvice(JoinPoint theJoinPoint) {

        // print out which method we are advising on
        String method = theJoinPoint.getSignature().toShortString();
        myLogger.info("\n=====>>> Executing @After (finally) on method: " + method);
    }

    @AfterThrowing(
            pointcut = "execution(* com.wild.aopdemo.dao.AccountDAO.findAccounts(..))",
            throwing = "theException"
    )
    public void afterThrowingFindAccountsAdvice(JoinPoint theJoinPoint, Throwable theException) {

        // print out which method we are advising on
        String method = theJoinPoint.getSignature().toShortString();
        myLogger.info("\n=====>>> Executing @AfterThrowing on method: " + method);

        // log the exception
        myLogger.info("\n=====>>> The exception is: " + theException);
    }

    // add a new advice for @AfterReturning on the findAccounts method
    @AfterReturning(
            pointcut = "execution(* com.wild.aopdemo.dao.AccountDAO.findAccounts(..))",
            returning = "result")
    public void afterReturningFindAccountsAdvice(JoinPoint theJoinPoint, List<Account> result) {

        // print out which method we are advising on
        String method = theJoinPoint.getSignature().toShortString();
        myLogger.info("\n=====>>> Executing @AfterReturning on method: " + method);

        // print out the results of the method call
        myLogger.info("\n=====>>> result is: " + result);

        // let's post-process the data ... let's modify it

        // convert the account name to uppercase
        convertAccountNamesToUpperCase(result);

        myLogger.info("\n=====>>> result is: " + result);
    }

    private void convertAccountNamesToUpperCase(List<Account> result) {

        // loop  through account
        for (Account tempAccount : result) {

            // get uppercase version of name
            String theUpperName = tempAccount.getName().toUpperCase();

            // update the name on the account
            tempAccount.setName(theUpperName);
        }
    }

    @Before(value = "com.wild.aopdemo.aspect.WildAopExpressions.forDaoPackageNoGetterSetter()")
    public void beforeAddAccountAdvice(JoinPoint theJoinPoint) {
        myLogger.info("\n=====>>> Executing @Before advice on method");

        // display the method signature
        MethodSignature theMethodSignature = (MethodSignature) theJoinPoint.getSignature();

        myLogger.info("Method: " + theMethodSignature);

        // display method arguments

        // get args
        Object[] args = theJoinPoint.getArgs();

        // loop thru args
        for (Object arg : args) {
            myLogger.info(arg.toString());

            if (arg instanceof Account) {

                // downcast and print Account specific stuff
                Account theAccount = (Account) arg;

                myLogger.info("Account name: " + ((Account) theAccount).getName());
                myLogger.info("Account level: " + ((Account) theAccount).getLevel());
            }
        }
    }
}
