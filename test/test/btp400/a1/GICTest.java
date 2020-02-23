package test.btp400.a1;

import com.seneca.accounts.*;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Ruby Anne Bautista
 * @since 2020/02/22
 * @version 1.0
 */
public class GICTest {

    @Test
	public void testEmpty() {
		GIC empty = new GIC();
        System.out.println(empty);
		assertEquals(empty, new GIC("","",0.00,0,0.00));
	}
	
	@Test
	public void testNull() {
		GIC nullGIC = new GIC (null, null, -1000.55, -1, -0.03);
		System.out.println(nullGIC);
		System.out.println(new GIC());
		assertEquals(nullGIC, new GIC());
	}

	@Test
	void testDeposit() {	
		GIC dep = new GIC("Daniel Derich", "TD001", 500.55, 2, 0.025);
		dep.deposit(199.99);
		GIC dep2 = new GIC("Daniel Derich", "TD001", 500.55, 2, 0.025);
		assertEquals(dep,dep2);
	}
    
    @Test
    public void WithdrawGICTest(){
        GIC withdrawG = new GIC("Fname Lname","1234G",100,4,0.10);
        assertFalse(withdrawG.withdraw(1));
    }

    @Test
    public void finalBalanceTest(){
        GIC b = new GIC("Fname Lname","1234G",1000,2,0.015);
        GIC c = new GIC("Fname1 Lname2","1234G",2000,2,0.022);
        double balance = b.getAccountBalance().doubleValue();
        double balance1 = c.getAccountBalance().doubleValue();
        assertEquals(1030.22,balance,2);
        assertEquals(2088.96,balance1,2);
    }
}
