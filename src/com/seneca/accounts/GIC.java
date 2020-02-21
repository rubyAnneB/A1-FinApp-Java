package com.seneca.accounts;

public class GIC extends Account
        implements Taxable {


    //Interface functions
    public void calculateTax(){

    }
    public double getTaxAmount(){

        return 100*tax_rate;
    }
    public String createTaxStatement(){

        return "Hello";
    }
}
