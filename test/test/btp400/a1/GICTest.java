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
    public void DepositGICTest(){
        GIC depositG = new GIC("Fname Lname","1234G",100,4,0.10);
        GIC depositG1 = new GIC("Fname Lname","1234G",100,4,0.10);
        depositG.deposit(100);
        assertEquals(depositG,depositG1);
    }
    @Test
    public void WithdrawGICTest(){
        GIC withdrawG = new GIC("Fname Lname","1234G",100,4,0.10);
        assertFalse(withdrawG.withdraw(1));
    }

    @Test
    public void finalBalanceTest(){
        GIC b = new GIC("Fname Lname","1234G",1000,2,0.015);
        double balance = b.getAccountBalance().doubleValue();
        assertEquals(1030.22,balance,2);
    }
}