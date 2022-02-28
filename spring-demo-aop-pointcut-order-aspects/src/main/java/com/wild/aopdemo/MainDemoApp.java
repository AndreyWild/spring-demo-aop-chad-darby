package com.wild.aopdemo;

import com.wild.aopdemo.configuration.DemoConfig;
import com.wild.aopdemo.dao.AccountDAO;
import com.wild.aopdemo.dao.MembershipDAO;
import com.wild.aopdemo.entity.Account;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainDemoApp {

    public static void main(String[] args) {

        // read spring config java class
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);

        // get the bean form spring container
        AccountDAO accountDAO = context.getBean("accountDAO", AccountDAO.class);
        MembershipDAO membershipDAO = context.getBean("membershipDAO", MembershipDAO.class);

        // call the business methods AccountDAO
        Account myAccount = new Account();
        accountDAO.addAccount(myAccount, true);
        accountDAO.doWork();

        // call the accountdao getter/setter
        accountDAO.setName("foobar");
        accountDAO.setServiceName("silver");

        String name = accountDAO.getName();
        String code = accountDAO.getServiceName();

        // call the business methods MembershipDAO
        membershipDAO.addSillyMember();
        membershipDAO.goToSleep();

        // close the context
        context.close();
    }
}