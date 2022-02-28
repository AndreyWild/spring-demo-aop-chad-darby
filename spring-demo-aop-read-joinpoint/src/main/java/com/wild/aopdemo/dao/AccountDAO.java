package com.wild.aopdemo.dao;

import com.wild.aopdemo.entity.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountDAO {

    private String name;
    private String serviceName;

    public String getName() {
        System.out.println(getClass() + ": in getName()");
        return name;
    }

    public void setName(String name) {
        System.out.println(getClass() + ": in setName()");
        this.name = name;
    }

    public String getServiceName() {
        System.out.println(getClass() + ": in getServiceName()");
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        System.out.println(getClass() + ": in setServiceName()");
        this.serviceName = serviceName;
    }

    public void addAccount(Account theAccount, boolean vipFlag){
        System.out.println(getClass() + ": DOING MY DB WORK: ADDING AN ACCOUNT");
    }

    public boolean doWork(){
        System.out.println(getClass() + ": doWork()");

        return false;
    }
}
