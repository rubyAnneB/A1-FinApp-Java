package com.seneca.accounts;

/**
 * @author Ruby Anne Bautista
 * @since 2020-02-21
 * @version 1.0
 */
public interface Taxable {
    public static final double tax_rate = .13; //"public static final" is very explicit and verbose

    public void calculateTax();
    public double getTaxAmount();
    public String createTaxStatement();
}
