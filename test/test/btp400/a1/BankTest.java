/*
 * Assignment 1
 * @author Daniel Derich
 * @version 1.0
 * @since 2020-02-22
 * */
package test.btp400.a1;

import com.seneca.accounts.*;
import com.seneca.business.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import java.math.BigDecimal;
import java.util.Arrays;

@RunWith(Suite.class)
@SuiteClasses({})
public class BankTest {

	@Test
	public void nullBank() {
		Bank b = new Bank(null);
		Bank a = new Bank();
		Chequing cheq = new Chequing("Dan Der", "TD2", 555.55, 0.30, 5);
		b.addAccount(cheq);
		a.addAccount(cheq);
		System.out.println(b);
		assertEquals(a, b);
	}

	@Test
	public void SearchBalance() {
		Bank b = new Bank("MyBank");
		Chequing a = new Chequing("John Doe", "3232", 144.12, 0.25, 3);
		Chequing c = new Chequing("John Doe", "3112", 144.12, 0.25, 3);
		Account fname = new Account("Fname Mname Lname", "3213", 1232.90909);
		Account draw = new Account("Fname Lname", "rr13", 1212.34);
		GIC gic = new GIC("Daniel D", "RBC333", 144.12, 2, 0.019);
		b.addAccount(a);
		b.addAccount(c);
		b.addAccount(fname);
		b.addAccount(draw);
		b.addAccount(gic);
		Account[] r = b.searchByBalance(144.12);

		System.out.println(Arrays.toString(r));
		assertEquals(r[0].getAccountBalance(), r[1].getAccountBalance());
	}

	@Test
	public void SearchName() {
		Bank b = new Bank("MyBank");
		Chequing a = new Chequing("John Doe", "3232", 144.12, 0.25, 3);
		Chequing c = new Chequing("John Doe", "3112", 144.12, 0.25, 3);
		Account fname = new Account("Fname Mname Lname", "3213", 1232.90909);
		Account draw = new Account("Fname Lname", "rr13", 1212.34);

		b.addAccount(a);
		b.addAccount(c);
		b.addAccount(fname);
		b.addAccount(draw);

		Account[] r = b.searchByAccountName("John Doe");

		System.out.println(Arrays.toString(r));
		assertEquals(r[0].getFullName(), r[1].getFullName());
	}

	@Test
	public void deleteTest() {
		Bank b = new Bank("MyBank");
		Chequing a = new Chequing("John Doe", "3232", 144.12, 0.25, 3);
		Chequing c = new Chequing("John Doe2", "3112", 144.12, 0.25, 3);
		Account fname = new Account("Fname Mname Lname", "3213", 1232.90909);
		Account draw = new Account("Fname Lname", "rr13", 1212.34);

		b.addAccount(a);
		b.addAccount(c);
		b.addAccount(fname);
		b.addAccount(draw);

		System.out.println(b);
		Account deleted = b.removeAccount("3232");
		System.out.println(b);

		System.out.println("This was deleted: " + deleted);

	}

	@Test
	public void BankgetAllTest() {
		Bank b = new Bank("MyBank");
		Chequing a = new Chequing("John Doe", "3232", 144.12, 0.25, 3);
		Chequing c = new Chequing("John Doe2", "3112", 144.12, 0.25, 3);
		Account fname = new Account("Fname Mname Lname", "3213", 1232.90909);
		Account draw = new Account("Fname Lname", "rr13", 1212.34);

		b.addAccount(a);
		b.addAccount(c);
		b.addAccount(fname);
		b.addAccount(draw);

		System.out.println(b);

		Account[] r = b.getAllAccounts();

		System.out.println(Arrays.toString(r));

	}

	@Test
	public void SearchAccountNum() {
		Bank myBank = new Bank();
		myBank.addAccount(new Chequing("John Doe", "1234C", 123.45, 0.25, 3));
		myBank.addAccount(new Chequing("Mary Ryan", "5678C", 678.90, 0.12, 3));

		Account edit = myBank.searchByAccountNumber("1234C");
		edit.withdraw(100);
		System.out.println(myBank);
	}

	@Test
	public void withdrawTester() {
		Bank myBank = new Bank();
		myBank.addAccount(new Chequing("John Doe", "1234C", 123.45, 0.25, 3));
		myBank.addAccount(new Chequing("Mary Ryan", "5678C", 678.90, 0.12, 3));
		Account[] contents = myBank.getAllAccounts();

		for (Account a : contents) {
			a.withdraw(100);
		}

		System.out.println(myBank);
	}

	@Test
	public void maxTransactionTest() {
		System.out.println("\n");
		Bank bank = new Bank("MyBank");
		Chequing ch1 = new Chequing("John Doe", "3232", 176.88, 0.25, 3);
		Chequing ch2 = new Chequing("John Johnston", "3112", 500.00, 0.25, 3);
		bank.addAccount(ch2);
		bank.addAccount(ch1);
		Account[] act = bank.searchByAccountName("John Doe");
		for (int i = 0; i < 4; ++i) {
			act[0].withdraw(10);
		}
	}
}
