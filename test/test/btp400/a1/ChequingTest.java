package test.btp400.a1;
import com.seneca.accounts.*;

import org.junit.Test;

import static org.junit.Assert.*;

public class ChequingTest {

    @Test
    public void construct(){
        Chequing a = new Chequing("John Doe","3232",123.12,0.25,3);
        System.out.println(a);
    }

    @Test
    public void equalTest(){
        Chequing a = new Chequing("John Doe","3232",123.12,0.25,3);
        Chequing b = new Chequing("John Doe","3232",123.12,0.25,3);
        Account c= new Account("John Doe","3232",123.12);


        System.out.println(a.equals(c));

    }

    @Test
    public void withdrawTest(){
        Chequing a = new Chequing("John Doe","3232",100.00,0.23,3);
        System.out.println(a);
        a.withdraw(1);
        System.out.println(a);
       a.withdraw(2);
        System.out.println(a);
        a.deposit(12);
        System.out.println(a);



    }

}