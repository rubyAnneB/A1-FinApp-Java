package com.seneca.business;
import java.math.BigDecimal;
import java.text.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.*;
import com.seneca.accounts.*;

/**
 * This class manages an ArrayList of accounts and provides
 * methods such as searching through the array through a
 * variety of ways
 * @author Ruby Anne Bautista
 * @version 1.0
 * @since 2020/01/20
 */
public class Bank {
    /**
     * An arrayList containing the accounts held by the bank
     */
    private ArrayList<Account> m_bankAccounts;
    /**
     * The name of the bank
     */
    private String m_bankName;

    /**
     * Sets the default Bank name to "Seneca@York"
     */
    public Bank(){
        this("Seneca@York");
    }

    public Bank(String name){
       if (name == null) {
           m_bankName = "Seneca@York";
       } else {
           m_bankName = name;
       }
        m_bankAccounts= new ArrayList<>(0);
    }

    /**
     * @return the bank's name
     */
    public String getBankName(){return m_bankName;}

    /**
     * Adds an account to the bank
     * @param newAccount the account to be added to the bank
     * @return Whether the addition of the passed account is successful for not
     */
    public boolean addAccount( Account newAccount ){

        for(Account acc:m_bankAccounts){
            if(newAccount==null||newAccount.getAccountNumber().equals(acc.getAccountNumber())){
                return false;
            }
        }

        return m_bankAccounts.add(newAccount);
    }

    /**
     * Removes an account from the bank
     * @param accountNumber the number of the account meant to be deleted
     * @return the deleted account
     */
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

    /**
     * Searches the bank's accounts for any accounts with the same balance as the passed parameter
     * @param balance the balance that needs to be searched for in the bank
     * @return an array of Accounts containing all the accounts with the same balance as the passed parameter
     */
    public Account[] searchByBalance(double balance){ //@returns an ArrayList<Account> containing any accounts with the same balance as the passed parameter

        ArrayList<Account> matches = new ArrayList<>(0);
        for(Account acc: m_bankAccounts){
            if(acc.getAccountBalance().compareTo(BigDecimal.valueOf(balance)) == 0){
                matches.add(acc);
            }
        }

        Account[] matchArray= new Account[matches.size()];
        matchArray = matches.toArray(matchArray);
        return matchArray;
    }

    /**
     * Searches the bank for any accounts with the same account name as the passed parameter
     * @param accountName The name that the accounts will be searched for
     * @return An array of Accounts with account names matching the passed parameter
     */
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

    /**
     * Searches the bank for any accounts with the same account number as the passed parameter
     * @param acc_Number The account number that the accounts will be searched for
     * @return An Account with account number matching the passed parameter
     */
    public Account searchByAccountNumber (String acc_Number){

        for(Account a: m_bankAccounts){
            if(a.getAccountNumber().equals(acc_Number)){
                return a;
            }
        }

        return null;
    }

    /**
     * Gets all the Accounts held by the bank
     * @return An Account array holding all of the accounts held by the bank
     */
    public Account [ ] getAllAccounts( ){
        Account[] a= new Account[m_bankAccounts.size()];
        return m_bankAccounts.toArray(a);
    }

    /**
     * Prints out the bank name and all the accounts held by the bank
     * @return the bank's information and the accounts held by the bank
     */
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
