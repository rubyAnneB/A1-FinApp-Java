/*
 * BTP400-Lab1
 * @author Ruby Anne Bautista
 * @version 1.0
 * @since 2020-01-26
 * */
package com.seneca.accounts;
import java.math.BigDecimal;
import java.text.*;

public class Account {
    private String m_name,
            m_num;
    private BigDecimal m_balance;

    public Account(String name, String num, double balance) {
        setAccountBalance(balance);
        setAccountNumber(num);
        setFullName(name);

    }


    public Account(){this("","",0);}

    //Ask peter why we should implement setters
    //@param name is the value set for the Account name. If null, the m_name is set as empty string
    private void setFullName( String name ) {
        m_name = (name == null) ? "" : name;
    }

    //@param num is the value set for the Account num. If null, the m_num is set as empty string
    private void setAccountNumber(String num){
        m_num = num==null?"":num;
    }

    //@param balance is the value set for the Account balance. If negative, m_balance is set as 0
    private void setAccountBalance (double balance){
        double n_balance = Math.max(balance, 0);
        m_balance =  BigDecimal.valueOf(balance);
    }


    //@return String returns the account name
    public String getFullName() {
        return m_name;
    }

    public String getFirstName(){
        String []nameSplit = m_name.split(" ");
        return nameSplit[0];
    }

    //@return all the tokens after the first space using " " as delimiter
    public String getLastName(){

        String []nameSplit = m_name.split(" ");
        String names="";
        if(nameSplit.length >=3 ){

            for(int i =1 ; i< nameSplit.length; ++i){
                names += nameSplit[i] + " ";
            }

        }else{
            names = nameSplit[nameSplit.length-1];
        }

        return names.trim();
    }

    //@return String returns the account number
    public String getAccountNumber(){
        return m_num;
    }

    //@return double returns the account balance
    public BigDecimal getAccountBalance (){
        return m_balance;
    }

    public String toString(){ //toString
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        StringBuffer str= new StringBuffer("\nAccount Name  : ");
        str.append(getLastName()).append(", ").append(getFirstName())
                .append("\nAccount Number: ").append(m_num)
                .append("\nCurrent Balance: ").append(nf.format(m_balance));
        return str.toString();

    }

    //Lab2


    @Override
    public int hashCode() {//ask more about this
        return this.getAccountNumber().hashCode();
    }

    @Override
    //@param takes an object (obj) and checks if the object is logically the same as the current object
    //@return true if the passed object is logically the same as the current object
    public boolean equals(Object obj) {

        if(this == obj){//referring to the same object therefore have same data
            return  true;
        }

        if((obj == null) || (obj.getClass() != this.getClass())){//obj is not of same class there cannot be same object
            return false;
        }

        Account acc = (Account) obj; //typecast obj for readability
        //Check the equality between all of the data fields
        //@return true if all values are equal
        return (this.getAccountBalance().equals(acc.getAccountBalance())&&
                (acc.getAccountNumber().equals(this.getAccountNumber())) &&
                (acc.getFullName().equals(this.getFullName())));
    }

    public boolean withdraw (double amount){
        //keep in mind that the balance must never become negative
        //Maybe create a custom exception for when balance turns negative?
        boolean result = false;

        if(amount>0){

            BigDecimal new_balance = ( m_balance.subtract(BigDecimal.valueOf(amount))); //Is is valid? Ask peter

            if(new_balance.compareTo(BigDecimal.ZERO)==1 ){//returns 1 if greater than passed param
                m_balance = new_balance;
                result = true;
            }

        }
        return result;
    }

    public void deposit(double amount){
        if(amount > 0){
            m_balance= m_balance.add(BigDecimal.valueOf(amount));
        }
    }
}
