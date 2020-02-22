package com.seneca.accounts;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Arrays;
//todo: Do the javadoc comments and other documentation

/**
 * @author Ruby Anne Bautista
 * @since 2020/02/21
 * @version 1.0
 *
 * This class is a type of Account. It keep track of the transactions that take place on the account
 * and applies the service charge base on the amount of transactions.
 */
public class Chequing extends Account {

    /**
     * The amount charged to the account with every transaction
     */
    private BigDecimal m_serv_charge;
    /**
     * Maximum amount of transactions that can occur on the account
     */
    private int m_max_trans;
    /**
     * Array holding all of the transactions represented by BigDecimals
     */
    private BigDecimal[] m_trans;
    /**
     * Sum of the service_charges on the account
     */
    private BigDecimal m_total_charges;

    public Chequing(){
        this("","",0,0.25,3);
    }

    public Chequing(String name, String num, double balance, double serveCharge, int maxTrans){
        super(name,num,balance);

        double charge = Math.max(serveCharge,0);
        m_serv_charge = new BigDecimal(charge);
        m_total_charges=new BigDecimal(0);
        m_max_trans = Math.max(maxTrans, 0);
        m_trans=new BigDecimal[0];


    }

    /**
     * @return The balance left on the account once
     * the total service charge has been subtracted from the account balance
     */
    @Override
    public BigDecimal getAccountBalance() {
        BigDecimal finalBalance = super.getAccountBalance();
        finalBalance = finalBalance.subtract(m_total_charges);
        return finalBalance;
    }

    /**
     *
     * @return Prints out the chequing account information
     */
    @Override
    public String toString() {
        NumberFormat nf = NumberFormat.getCurrencyInstance();

        StringBuffer str = new StringBuffer();
        str.append(super.toString())
                .append("\nAccount Type        : CHQ")
                .append("\nService Charge      : ").append(nf.format(m_serv_charge))
                .append("\nTotal Charges       : ").append(nf.format(m_total_charges))
                .append("\nList of Transactions: ").append(transationArrayString())
                .append("\nFinal Balance       : ").append(nf.format(getAccountBalance()));

        return str.toString();

    }

    /**
     * @return Formats the array of transactions into a
     * String to be printed out by the toString() method
     */
    private String transationArrayString(){

        String tranString= "";

        for(BigDecimal b : m_trans){
            tranString += (b.compareTo(BigDecimal.ZERO) > 0) ? ("+"+(b)+", "  ): (b)+", ";//todo: last comma is printing
        }

        return tranString;

    }
    public boolean equals(Object obj) {

        if((obj == null) || (obj.getClass() != this.getClass())){//obj is not of same class there cannot be same object
            return false;
        }

        if(!super.equals(obj)){
            return false;
        }

        Chequing acc = (Chequing) obj;

        boolean ser_charge = (this.m_serv_charge.equals(acc.m_serv_charge)),
                mx_trans = (this.m_max_trans==acc.m_max_trans),
                trans= (Arrays.equals(this.m_trans, acc.m_trans));

        return (ser_charge&&mx_trans&&trans);
    }

    @Override
    //todo:Do more research on hashCode
    public int hashCode() {
        return super.hashCode();
    }

    /**
     * Calls the superclass' (Account) deposit method and stores the amount deposited into the
     * transaction array
     * @param amount the amount to be deposited
     */
    @Override
    public void deposit(double amount) {
        super.deposit(amount);
        store_trans(amount);
    }

    /**
     * Calls the superclass' (Account) withdraw method and stores the amount withdrawn
     * into the transaction
     * @param amount the amount to be withdrawn. Value must be positive
     * @return returns true if withdrawal is successful, false if failed
     */
    @Override
    public boolean withdraw(double amount) {
        boolean res = store_trans(amount*-1);

        if(res){
            res=super.withdraw(amount);
        }

        return res;
    }

    /**
     * Stores the transactions
     * @param amount the amount withdrawn or deposited into the account the transaction. Withdraw
     *               amounts are expressed as negative values, while deposits are expressed as postive.
     * @return true if transaction has been successfully stored into the transaction array, false if not
     */
    private boolean store_trans (double amount){
        boolean res = false;
        if(m_trans.length < m_max_trans){
            m_trans = Arrays.copyOf(m_trans,m_trans.length+1);
            m_trans[m_trans.length-1]= BigDecimal.valueOf(amount);
            m_total_charges=m_total_charges.add(m_serv_charge);
            res = true;

        }else{//you can make a custome exception for this
            System.out.println("store_trans: max amount of transactions reached");
        }

        return res;
    }
}
