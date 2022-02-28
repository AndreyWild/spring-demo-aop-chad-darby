package com.wild.aopdemo;

import com.wild.aopdemo.configuration.DemoConfig;
import com.wild.aopdemo.dao.AccountDAO;
import com.wild.aopdemo.entity.Account;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class AfterReturningDemoApp {

    public static void main(String[] args) {

        // read spring config java class
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);

        // get the bean form spring container
        AccountDAO accountDAO = context.getBean("accountDAO", AccountDAO.class);

        // call method to fine the accounts
        List<Account> theAccounts = accountDAO.findAccounts();

        // display the accounts
        System.out.println("\n\nMain Program: AfterReturningDemoApp");
        System.out.println("---");

        System.out.println(theAccounts);

        System.out.println("\n");

        System.out.println("=================================================");
        Account tempAccount = accountDAO.getOneAccount();


        System.out.println("modifyName = " + tempAccount);

        // close the context
        context.close();
    }
}
// 287