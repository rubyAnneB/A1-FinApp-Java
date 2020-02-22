package com.seneca.accounts;

/**
 * @author Ruby Anne Bautista
 * @since 2020-02-21
 * @version 1.0
 */

/**
 * This is the interface for any taxable accounts
 */
public interface Taxable {
    /**
     * This is the tax rate defined for all taxable accounts
     */
    public static final double tax_rate = .13;

    public void calculateTax();
    public double getTaxAmount();
    public String createTaxStatement();
}
