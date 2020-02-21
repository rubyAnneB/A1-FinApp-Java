package com.seneca.business;

import com.seneca.accounts.Account;
import com.seneca.accounts.Chequing;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.Assert.*;

public class BankTest {

    @Test
    public void deleteTest(){
        Bank b = new Bank("MyBank");
        Chequing a = new Chequing("John Doe","3232",144.12,0.25,3);
        Chequing c = new Chequing("John Doe2","3112",144.12,0.25,3);
        Account fname= new Account("Fname Mname Lname","3213",1232.90909);
        Account draw= new Account("Fname Lname","rr13",1212.34);

        b.addAccount(a);
        b.addAccount(c);
        b.addAccount(fname);
        b.addAccount(draw);


        System.out.println(b);
        Account deleted = b.removeAccount("3232");
        System.out.println(b);

        System.out.println("This was deleted: "+ deleted);

    }

    @Test

    public void BankgetAllTest(){
        Bank b = new Bank("MyBank");
        Chequing a = new Chequing("John Doe","3232",144.12,0.25,3);
        Chequing c = new Chequing("John Doe2","3112",144.12,0.25,3);
        Account fname= new Account("Fname Mname Lname","3213",1232.90909);
        Account draw= new Account("Fname Lname","rr13",1212.34);

        b.addAccount(a);
        b.addAccount(c);
        b.addAccount(fname);
        b.addAccount(draw);


        System.out.println(b);

        Account[] r = b.getAllAccounts();

        System.out.println(Arrays.toString(r));



    }

    @Test
    public void SearchName(){
        Bank b = new Bank("MyBank");
        Chequing a = new Chequing("John Doe","3232",144.12,0.25,3);
        Chequing c = new Chequing("John Doe","3112",144.12,0.25,3);
        Account fname= new Account("Fname Mname Lname","3213",1232.90909);
        Account draw= new Account("Fname Lname","rr13",1212.34);

        b.addAccount(a);
        b.addAccount(c);
        b.addAccount(fname);
        b.addAccount(draw);

        Account[] r = b.searchByAccountName("John Doe");

        System.out.println(Arrays.toString(r));


    }


    @Test
    public void SearchBalance(){
        Bank b = new Bank("MyBank");
        Chequing a = new Chequing("John Doe","3232",144.12,0.25,3);
        Chequing c = new Chequing("John Doe","3112",144.12,0.25,3);
        Account fname= new Account("Fname Mname Lname","3213",1232.90909);
        Account draw= new Account("Fname Lname","rr13",1212.34);

        b.addAccount(a);
        b.addAccount(c);
        b.addAccount(fname);
        b.addAccount(draw);

        Account[] r = b.searchByBalance(144.12);

        System.out.println(Arrays.toString(r));


    }

    @Test
    public void WithdrawTest(){
        Bank myBank = new Bank();
        myBank.addAccount(new Chequing("John Doe","1234C",123.45,0.25,3));
        myBank.addAccount(new Chequing("Mary Ryan","5678C",678.90,0.12,3));

        Account[] contents= myBank.getAllAccounts();//this is dangerous- I'm able to make a copy of a private variable that I can then modify

        for(Account a: contents){
            a.withdraw(100);
        }

        System.out.println(myBank);
    }

    @Test
    public void SearchAccountNum(){
        Bank myBank = new Bank();
        myBank.addAccount(new Chequing("John Doe","1234C",123.45,0.25,3));
        myBank.addAccount(new Chequing("Mary Ryan","5678C",678.90,0.12,3));

        Account edit = myBank.searchByAccountNumber("1234C");
        edit.withdraw(100);
        System.out.println(myBank);
    }

}