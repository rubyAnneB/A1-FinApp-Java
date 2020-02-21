/*
* BTP400-Lab2
* @author Ruby Anne Bautista
* @version 1.0
* @since 2020/01/20
* */

package com.seneca.business;
import java.math.BigDecimal;
import java.text.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.*;

import com.seneca.accounts.*;
public class Bank {
    private ArrayList<Account> m_bankAccounts;
    private String m_bankName;

    public Bank(){
        this("Seneca@York");
    }

    public Bank(String name){
        m_bankName=name;
        m_bankAccounts=new ArrayList<Account>(0);
    }

    public String getBankName(){return m_bankName;}

    public boolean addAccount( Account newAccount ){//@return a boolean based on whether the passed object has been successfully added to the bank ArrayList

        for(Account acc:m_bankAccounts){
            if(newAccount==null||newAccount.getAccountNumber().equals(acc.getAccountNumber())){
                return false;
            }
        }

        return m_bankAccounts.add(newAccount);
    }

    public Account removeAccount(String accountNumber){ //todo: Ask peter about this return type- makes better sense if it were bool no?

        Account m = null;
        for(Account a : m_bankAccounts){
            if(a.getAccountNumber().equals(accountNumber)){
                m = a;
            }
        }
        m_bankAccounts.removeIf(a -> a.getAccountNumber().equals(accountNumber));
        return m;
    }

    public Account[] searchByBalance(double balance){ //@returns an ArrayList<Account> containing any accounts with the same balance as the passed parameter

        ArrayList<Account> matches = new ArrayList<Account>(0);
        for(Account acc: m_bankAccounts){
            if(acc.getAccountBalance().compareTo(BigDecimal.valueOf(balance)) == 0){
                matches.add(acc);
            }
        }

        Account[] matchArray= new Account[matches.size()];
        matchArray = matches.toArray(matchArray);
        return matchArray;
    }
    public Account [ ] searchByAccountName( String accountName ){
        ArrayList<Account> match = new ArrayList<>();

        for(Account a: m_bankAccounts){
            if(a.getFullName().matches(accountName)){
                match.add(a);
            }
        }

        Account[] matchArray = new Account[match.size()];
        return match.toArray(matchArray);
    }

    public Account searchByAccountNumber (String acc_Number){

        for(Account a: m_bankAccounts){
            if(a.getAccountNumber().equals(acc_Number)){
                return a;
            }
        }

        return null;
    }

    public Account [ ] getAllAccounts( ){
        Account[] a= new Account[m_bankAccounts.size()];
        return m_bankAccounts.toArray(a);
    }
    public String toString(){
        NumberFormat nf = NumberFormat.getCurrencyInstance();

        StringBuffer out = new StringBuffer();

        out.append("*** Welcome to the Bank of ").append(m_bankName).append(" ***\n").append(" It has ").append(m_bankAccounts.size()).append(" accounts." );

        int i=1;
        for(Account a:m_bankAccounts){
            out.append("\n").append(i).append(". ").append("number: ")
                    .append(a.getAccountNumber()).append(", name: ").append(a.getFullName()).append(" balance: ").append(nf.format(a.getAccountBalance()));
            ++i;
        }


        return out.toString();
    }

    @Override
    public boolean equals(Object obj) {

        if(this == obj){//referring to the same object therefore have same data
            return  true;
        }

        if((obj == null) || (obj.getClass() != this.getClass())){//obj is not of same class there cannot be same object
            return false;
        }

        Bank compare = (Bank) obj;

        return (compare.getBankName().equals(this.m_bankName) && compare.m_bankAccounts.equals(this.m_bankAccounts));
    }

    @Override
    public int hashCode() {//look in Employee- dw about this
        return m_bankAccounts.hashCode();
    }



}
