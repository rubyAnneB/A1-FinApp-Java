/*
 * Assignment 1
 * @author Daniel Derich
 * @version 1.0
 * @since 2020-02-22
 * */

package test.btp400.a1;
import com.seneca.accounts.Account;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

import java.text.NumberFormat;

public class AccountTest {

    @Test
    public void neg_InitBalance(){
        Account neg = new Account("John Smith", "2323",-323232);
        assertEquals(BigDecimal.ZERO,neg.getAccountBalance());
    }
    @Test
    public void nullName(){
        Account noName = new Account(null, "2323",-323232);
        assertEquals("",noName.getFullName());

    }
    @Test
    public void getFirstName() {
        Account fname= new Account("Fname Mname Lname","3213",1232.90909);
        assertEquals("Fname",fname.getFirstName());
    }

    @Test
    public void getLastName() {
        Account lname= new Account("Fname Lname","3213",12.909090);
        assertEquals("Lname",lname.getLastName());
    }

    @Test
    public void noLastName(){
        Account lname= new Account("Fname","3213",12);
        assertEquals("Fname",lname.getLastName());
    }

    @Test
    public void manyNames(){
        Account nameS= new Account("Fname Mname Lname","3213",12);
        assertEquals("Mname Lname",nameS.getLastName());

    }

    @Test
    public void withdrawPositive() {
        Account draw= new Account("Fname Mname Lname","3213",1212.34);
        assertTrue(draw.withdraw(2));
        System.out.println(draw);

    }

    @Test
    public void withdrawNegative(){
        Account draw= new Account("Fname Mname Lname","3213",1212.34);
        assertFalse(draw.withdraw(-12));
        System.out.println(draw);
    }

    @Test
    public void withdrawInsufficient(){
        Account draw= new Account("Fname Mname Lname","3213",1212.34);
        assertFalse(draw.withdraw(1212121212));
        System.out.println(draw);
    }

    @Test
    public void depositNeg() {
        Account depNeg = new Account("John Smoth", "34242", 0);
        depNeg.deposit(-123.00);
        System.out.println(depNeg);
    }

    @Test
    public void depositPos() {
        Account depPos= new Account("John Smoth", "34242", 0);
        depPos.deposit(123.00);
        System.out.println(depPos);
    }


}
