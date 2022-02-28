package com.wild.aopdemo;

import com.wild.aopdemo.configuration.DemoConfig;
import com.wild.aopdemo.dao.AccountDAO;
import com.wild.aopdemo.entity.Account;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class AfterThrowingDemoApp {

    public static void main(String[] args) {

        // read spring config java class
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);

        // get the bean form spring container
        AccountDAO accountDAO = context.getBean("accountDAO", AccountDAO.class);

        // call method to fine the accounts
        List<Account> theAccounts = null;

        try {
            boolean tripWire = true;
            theAccounts = accountDAO.findAccounts(tripWire);
        } catch (Exception exc) {
            System.out.println("\n\nMain Program ... caught exception: " + exc);
        }

        // display the accounts
        System.out.println("\n\nMain Program: AfterReturningDemoApp");
        System.out.println("---");

        System.out.println(theAccounts);

        System.out.println("\n");

        // close the context
        context.close();
    }
}
// 287