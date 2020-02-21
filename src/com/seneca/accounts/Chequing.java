package com.seneca.accounts;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Arrays;
//todo: Do the javadoc comments and other documentation
public class Chequing extends Account {

    private BigDecimal m_serv_charge;
    private int m_max_trans;
    private BigDecimal[] m_trans;
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

    @Override
    public BigDecimal getAccountBalance() {//this is being called from the super class
        BigDecimal finalBalance = super.getAccountBalance();
        finalBalance = finalBalance.subtract(m_total_charges);
        return finalBalance;
    }

    @Override
    //todo: fix the formating
    public String toString() {
        NumberFormat nf = NumberFormat.getCurrencyInstance();

        StringBuffer str = new StringBuffer("");
        str.append(super.toString()).append("\nAccount Type: CHQ")
                .append("\nService Charge: ").append(nf.format(m_serv_charge))
                .append("\nTotal Charges:").append(nf.format(m_total_charges))
                .append("\nList of Transactions:").append(Arrays.toString(m_trans))//todo: this is not showing the sign for positive numbers- fix this
                .append("\nFinal Balance:").append(nf.format(getAccountBalance()));

        return str.toString();

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

    @Override
    public void deposit(double amount) {
        super.deposit(amount);
        store_trans(amount);
    }

    @Override
    public boolean withdraw(double amount) {
        boolean res = store_trans(amount*-1);;

        if(res){
            res=super.withdraw(amount);
        }

        return res;
    }

    boolean store_trans (double amount){
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
