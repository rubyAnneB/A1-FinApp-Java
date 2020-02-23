/*
 * Assignment 1
 * @author Daniel Derich
 * @version 1.1
 * @since 2020-02-22
 * */

package com.seneca.accounts;

import java.math.BigDecimal;
import java.text.NumberFormat;

public class GIC extends Account implements Taxable {
	private int m_period;
	private BigDecimal m_rate;
	private BigDecimal taxAmount = new BigDecimal("0.00");
	private BigDecimal intIncome = new BigDecimal("0.00");

	/**
	 * constructs empty GIC with default values
	 */
	public GIC() {
		this("", "", 0.00, 1, 0.0125);

	}

	/**
	 * This constructs a Account->GIC object
	 * 
	 * @param name      the full name of the customer
	 * @param acctnum   the customer's account number
	 * @param principal the starting balance of GIC
	 * @param period    the total # of years
	 * @param rate      the interest rate
	 */
	public GIC(String name, String acctnum, double principal, int period, double rate) {
		super(name, acctnum, principal);
		m_period = period;
		m_rate = BigDecimal.valueOf(rate);
	}

	/**
	 * This is an override of the equals() method
	 * 
	 * @param obj : Object - checked by if instanceof
	 * @return status : boolean returns true if all attributes are equal
	 */
	public boolean equals(Object objG) {
		boolean status = false;
		if (objG instanceof GIC) {
			GIC g2 = (GIC) objG;
			if (super.equals(g2) == true && this.m_period == g2.m_period && this.m_rate.equals(g2.m_rate)) {
				status = true;
			}

		}
		return status;
	}

	/**
	 * toString()
	 * 
	 * @return result.toString() : String - prints out all private attributes in
	 *         format
	 */
	public String toString() {
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		NumberFormat rt = NumberFormat.getNumberInstance();
		rt.setMinimumFractionDigits(2);
		rt.setMaximumFractionDigits(2);
		StringBuffer result = new StringBuffer();
		result.append(super.toString());
		result.append("\nAccount Type               : GIC\n").append("Annual Interest Rate       : ")
				.append(nf.format(m_rate.doubleValue() * 100.00));
		result.append("%\nPeriod of Investment       : ").append(m_period)
				.append(" year(s)\n" + "Interest Income at Maturity: ");
		result.append(nf.format(intIncome.doubleValue())).append("\nBalance at Maturity        : ");
		result.append(nf.format(this.getAccountBalance().doubleValue())).append("\n");
		return result.toString();
	}

	/**
	 * hashCode()
	 * 
	 * @return superclass hashcode
	 */
	public int hashCode() {
		return super.hashCode();
	}

	/**
	 * Interface method Calculates total taxes on balance at maturity
	 * 
	 */
	public void calculateTax() {
		BigDecimal amount = this.getAccountBalance().multiply(BigDecimal.valueOf(tax_rate));
		taxAmount = amount;
	}

	/**
	 * Interface method
	 * 
	 * @return the total tax amount, priuvate variable getters method
	 */
	public double getTaxAmount() {
		this.calculateTax();
		return taxAmount.doubleValue();
	}

	/**
	 * Interface method
	 * 
	 * @return str : String - a listing of the amount of interest, new balance, tax
	 *         accumulated.
	 */
	public String createTaxStatement() {
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		StringBuffer str = new StringBuffer();
		str.append("Tax rate       : ").append((int) (tax_rate * 100.00)).append("%\nAccount Number : ").append(this.getAccountNumber());
		str.append("\nInterest Income: ").append(nf.format(this.getAccountBalance().doubleValue())).append("\nAmount of tax  : ");
		str.append(nf.format(getTaxAmount())).append("\n");
		return str.toString();
	}

	@Override // empty as GIC does not allow deposits
	public void deposit(double amount) {
	}

	@Override // returns false always because GIC does not allow withdrawals
	public boolean withdraw(double amount) {
		return false;
	}

	/** getAccountBalance()
	 * @return BigDecimal of Balance at Maturity Formula: Current/Starting Balance x
	 *         ( 1 + r ) ^ t r = annual interest rate t = number of years (i.e.
	 *         period of investment)
	 */
	@Override
	public BigDecimal getAccountBalance() {
		BigDecimal start = super.getAccountBalance();
		BigDecimal mature;
		mature = start.multiply((m_rate.add(BigDecimal.valueOf(1.00))).pow(m_period));
		this.intIncome = mature.subtract(start);
		return mature;
	}
	/**
	 * 
	 * @return str.toString() - String that holds formatted information about a Taxable GIC Account
	 */
	public String getTax() {
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		StringBuffer str = new StringBuffer();
		//str.append("Tax rate: ").append((int) (tax_rate * 100.00)).append("%\n");
		str.append("Account Number : ").append(this.getAccountNumber());
		str.append("\nInterest Income: ").append(nf.format(this.getAccountBalance().doubleValue())).append("\nAmount of tax  : ");
		str.append(nf.format(getTaxAmount())).append("\n");
		return str.toString();
	}
}
