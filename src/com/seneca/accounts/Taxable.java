package com.seneca.accounts;

public interface Taxable {
    public static final double tax_rate = .13; //"public static final" is very explicit and verbose

    public void calculateTax();
    public double getTaxAmount();
    public String createTaxStatement();
}
