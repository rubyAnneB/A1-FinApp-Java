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
    double tax_rate = .15;

    void calculateTax();
    double getTaxAmount();
    String createTaxStatement();
}
