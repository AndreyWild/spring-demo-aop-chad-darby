package com.wild.aopdemo.dao;

import com.wild.aopdemo.entity.Account;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountDAO {

    private String name;
    private String serviceName;

    // add a new method: findAccounts()
    public List<Account> findAccounts(boolean tripWire){

        // for academic purpose ... simulate an exception
        if (tripWire){
            throw new RuntimeException("No soup for you!");
        }

        List<Account> myAccounts = new ArrayList<>();

        // create sample accounts
        Account temp1 = new Account("John", "Silver");
        Account temp2 = new Account("Wild", "Platinum");
        Account temp3 = new Account("Luca", "Gold");

        // add them to our accounts list
        myAccounts.add(temp1);
        myAccounts.add(temp2);
        myAccounts.add(temp3);

        return myAccounts;
    }

    public Account getOneAccount(){
        return new Account("Name", "serviceName");
    }

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
