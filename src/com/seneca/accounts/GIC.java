/*
 * Assignment 1
 * @author Daniel Derich
 * @version 1.0
 * @since 2020-02-20
 * */

package com.seneca.accounts;

import java.math.BigDecimal;
import java.text.NumberFormat;

//Todo:Review code

public class GIC extends Account implements Taxable {
	private int m_period;
	private BigDecimal m_rate;
	private BigDecimal taxAmount = new BigDecimal("0.00"); // ???????
	private BigDecimal intIncome = new BigDecimal("0.00");

	GIC() {
		this("", "", 0.00, 1, 0.0125);
		
	}

	GIC(String name, String acctnum, double principal, int period, double rate) {
		super(name, acctnum, principal);
		m_period = period;
		m_rate = BigDecimal.valueOf(rate);
	}

	public boolean equals(Object objG) {
		boolean status = false;
		if (objG instanceof GIC) {
			GIC g2 = (GIC) objG;
			if (super.equals(g2) &&
					this.m_period == g2.m_period &&
					this.m_rate.equals(g2.m_rate))
			{
				status = true;
			}
				
		}
		return status;
	}

	public String toString() {
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		NumberFormat rt = NumberFormat.getNumberInstance();
		rt.setMinimumFractionDigits(2);
		rt.setMaximumFractionDigits(2);
		StringBuffer result = new StringBuffer();
		result.append(super.toString());
		result.append("\nAccount Type               : GIC\n").append("Annual Interest Rate       : ").append(nf.format(m_rate.doubleValue() * 100.00));
		result.append("%\nPeriod of Investment       : ").append(m_period).append(" year(s)\n" + "Interest Income at Maturity: ");
		result.append(nf.format(intIncome.doubleValue())).append("\nBalance at Maturity        : ");
		result.append(nf.format(this.getAccountBalance().doubleValue())).append("\n");
		return result.toString();
	}

	public int hashCode() {
		 return super.hashCode();
	}

	// Interface methods
	public void calculateTax() {
		BigDecimal amount = this.getAccountBalance().multiply(BigDecimal.valueOf(tax_rate));
		taxAmount = amount;
	}

	public double getTaxAmount() {
		this.calculateTax();
		return taxAmount.doubleValue();
	}

	public String createTaxStatement() {//todo:Remind - to fix this to use StringBuffer
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		String str = "Tax rate       : " + (int)(tax_rate * 100.00) + "%\nAccount Number : " + 
	this.getAccountNumber() + "\nInterest income: " + nf.format(this.getAccountBalance().doubleValue())
	+ "\nAmount of tax  : " + nf.format(getTaxAmount()) + "\n";
		return str;
	}

	// override of Account
	public void deposit(double amount) {
	} // done

	// override of Account
	public boolean withdraw(double amount) {
		return false;
	} // done
	
	public BigDecimal getAccountBalance() {
		BigDecimal start = super.getAccountBalance();
		BigDecimal mature;
		mature = start.multiply((m_rate.add(BigDecimal.valueOf(1.00))).pow(m_period));
		this.intIncome = mature.subtract(start);
		return mature;
	}
}