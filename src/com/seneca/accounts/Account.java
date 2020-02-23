package com.seneca.accounts;
import java.math.BigDecimal;
import java.text.*;

/**
 * @author Ruby Anne Bautista
 * @since 2020-02-21
 * @version 2.0
 *
 * This class acts as the superclass for all other accounts
 */

public class Account {

    private String m_name,
            m_num;
    private BigDecimal m_balance;

    /**
     *
     * @param name Name of person who owns the account
     * @param num the account number
     * @param balance the starting balance of the account
     */
    public Account(String name, String num, double balance) {
        setAccountBalance(balance);
        setAccountNumber(num);
        setFullName(name);

    }

    /**
     * This default constuctor- sets the default values of the account
     */
    public Account(){this("","",0);}


    /**
     * @param name is the value set for the Account name. If null, the m_name is set as empty string
     */
    private void setFullName( String name ) {
        m_name = (name == null) ? "" : name;
    }



    /**
     * @param num is the value set for the Account num. If null, the m_num is set as empty string
     */
    private void setAccountNumber(String num){
        m_num = num==null?"":num;
    }



    /**
     * @param balance  is the value set for the Account balance. If negative, m_balance is set as 0
     */
    private void setAccountBalance (double balance){
        double n_balance = Math.max(balance, 0);
        m_balance =  BigDecimal.valueOf(n_balance);
    }



    /**
     *
     * @return String returns the account name
     */
    public String getFullName() {
        return m_name;
    }

    /**
     * Returns the first name
     * @return first name, the name before the first encountered space
     */
    public String getFirstName(){
        String []nameSplit = m_name.split(" ");
        return nameSplit[0];
    }


    /**
     *
     * @return all the tokens after the first space using " " as delimiter
     */
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

    /**
     * @return String returns the account number
     */
    public String getAccountNumber(){
        return m_num;
    }

    /**
     * @return double returns the account balance
     */
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

    @Override
    public int hashCode() {//ask more about this
        return this.getAccountNumber().hashCode();
    }

    /**
     * @param obj takes an object (obj) and checks if the object is logically the same as the current object
     *     //@return true if the passed object is logically the same as the current object
     */
    @Override
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

    /**
     * Withdraws amount from the account
     * @param amount the amount the user would like to withdraw from the account. Cannot be negative
     * @return the result of the attempting withdraw. If passed amount is negative, will return a false
     */
    public boolean withdraw (double amount){

        boolean result = false;

        if(amount>0){

            BigDecimal new_balance = ( m_balance.subtract(BigDecimal.valueOf(amount))); //Is is valid? Ask peter

            if(new_balance.compareTo(BigDecimal.ZERO) > 0){//returns 1 if greater than passed param
                m_balance = new_balance;
                result = true;
            }

        }
        return result;
    }

    /**
     * @param amount the amount the user would like to deposit in to the account. Cannot be negative
     */
    public void deposit(double amount){
        if(amount > 0){
            m_balance= m_balance.add(BigDecimal.valueOf(amount));
        }
    }
}
