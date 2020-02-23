/*
 * Assignment 1
 * @author Daniel Derich
 * @version 1.0
 * @since 2020-02-223
 * */
package test.btp400.a1;

import com.seneca.accounts.*;

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
public class ChequingTest {

	@Test
	public void emptyconstruct() {
		Chequing cheq = new Chequing();
		Chequing cheq2 = new Chequing(null, null, -5.22, -0.25, -2);
		System.out.println(cheq);
		System.out.println(cheq2);
	}

	public void withdrawNeg() {
		Chequing a = new Chequing("John Doe", "3232", 100.00, 0.23, 3);

		assertFalse(a.withdraw(-10));
		System.out.println(a.getAccountBalance());
	}

	@Test
	public void maxTransact() {
		Chequing a = new Chequing("John Doe", "3232", 100.00, 0.25, 2);
		a.withdraw(10);
		a.withdraw(10);
		a.withdraw(10); // expected error printed
		double balance = a.getAccountBalance().doubleValue();
		assertEquals(79.50, balance, 2);
	}

	@Test
	public void negBalance() {
		Chequing cheq = new Chequing("Daniel Derich", "TD01", 1000.50, 0.25, 5);
		cheq.withdraw(1002);
		System.out.println("negBalance(): " + cheq.getAccountBalance().doubleValue());
		double balance = cheq.getAccountBalance().doubleValue();
		assertTrue(balance > 0.00);
	}

	@Test
	public void depositCorrect() {
		Chequing cheq = new Chequing("Daniel Derich", "TD01", 1000.55, 0.10, 5);
		cheq.deposit(100.50);
		cheq.withdraw(58.99);
		double bal = 1000.55 + 100.50 - 58.99;
		double bal2 = cheq.getAccountBalance().doubleValue();
		assertEquals(bal, bal2, 2);
	}
	
	@Test
	public void serviceCharge() {
		Chequing cheq = new Chequing("Daniel Derich", "TD01", 1000.55, 0.10, 5);
		cheq.deposit(100.50);
		cheq.withdraw(58.99);
		cheq.deposit(1);
		System.out.println(cheq); // total transactions should be $0.30
	}
}
