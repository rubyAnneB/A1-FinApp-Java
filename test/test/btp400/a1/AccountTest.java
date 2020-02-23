/*
 * Assignment 1
 * @author Daniel Derich
 * @version 1.0
 * @since 2020-02-22
 * */
package test.btp400.a1;

import com.seneca.accounts.*;
//import com.seneca.business.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import java.math.BigDecimal;

@RunWith(Suite.class)
@SuiteClasses({})
public class AccountTest {
	@Test
	public void testEmpty() {
		Account ac = new Account();
		System.out.println(ac);
		assertEquals(ac, new Account("", "", 0.00));
	}

	@Test
	public void neg_InitBalance() {
		Account neg = new Account("John Smith", "2323", -323232);
		assertEquals(BigDecimal.valueOf(0.00), neg.getAccountBalance());
	}

	@Test
	public void testNullAndNeg() {
		Account nullAcc = new Account(null, null, -5);
		System.out.println(nullAcc);
		assertEquals(nullAcc, new Account("", "", 0.00));
	}

	@Test
	public void withdrawNegative() {
		Account draw = new Account("John Martin", "3213", 1212.34);
		assertFalse(draw.withdraw(-55));
		System.out.println(draw);
	}

	@Test
	public void withdrawPositive() {
		Account draw = new Account("Fname Mname Lname", "3213", 1212.34);
		assertTrue(draw.withdraw(2));
		System.out.println(draw);

	}

	@Test
	public void negBalance() {
		Account draw = new Account("Dan Harper", "5678", 1000);
		assertFalse(draw.withdraw(1001.11));
	}

	@Test
	public void depositNeg() {
		Account depNeg = new Account("John Smoth", "34242", 0);
		BigDecimal bal = depNeg.getAccountBalance();
		depNeg.deposit(-123.00);
		assertEquals(bal, depNeg.getAccountBalance());
	}

	@Test
	public void finalBal() {
		Account a = new Account("Daniel D", "TD01", 1000.00);
		a.deposit(200.00);
		a.withdraw(50.00);
		assertEquals(BigDecimal.valueOf(1000.00 + 200.00 - 50.00), a.getAccountBalance());
	}
}
