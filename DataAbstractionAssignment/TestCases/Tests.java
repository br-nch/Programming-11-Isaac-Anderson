import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import static org.junit.Assert.assertEquals;

public class Tests {
Customer test;
Date today;

    @Before
    public void setUp(){
        test = new Customer();
        today = new Date();
        today.setTime(System.currentTimeMillis());
    }


    @Test
    public void testCustomerDeposit(){
        assertEquals(0, test.deposit(0, today ,Customer.SAVING), 0.0);
        assertEquals(100, test.deposit(100, today, Customer.SAVING), 0.0);
        assertEquals(150, test.deposit(50, today, Customer.SAVING), 0.0);
        assertEquals(0, test.deposit(0, today ,Customer.CHECKING), 0.0);
        assertEquals(100, test.deposit(100, today, Customer.CHECKING), 0.0);
        assertEquals(150, test.deposit(50, today, Customer.CHECKING), 0.0);
    }
    @Test
    public void testCustomerWithdraw(){
        test.deposit(200, today, Customer.SAVING);
        assertEquals(200, test.withdraw(0, today, Customer.SAVING), 0.0);
        assertEquals(100, test.withdraw(100, today, Customer.SAVING), 0.0);
        assertEquals((-50), test.withdraw(150, today, Customer.SAVING), 0.0);
        assertEquals((-100), test.withdraw(100, today, Customer.SAVING), 0.0);
        test.deposit(200, today, Customer.CHECKING);
        assertEquals(200, test.withdraw(0, today, Customer.CHECKING), 0.0);
        assertEquals(100, test.withdraw(100, today, Customer.CHECKING), 0.0);
        assertEquals((-50), test.withdraw(150, today, Customer.CHECKING), 0.0);
        assertEquals((-100), test.withdraw(100, today, Customer.CHECKING), 0.0);
    }
    @Test
    public void testDepositString(){
        Deposit a = new Deposit(0,today,Customer.SAVING);
        assertEquals("Deposit of: $0.0 Date: " + today + " into account: Saving", a.toString());
        a = new Deposit(100,today,Customer.SAVING);
        assertEquals("Deposit of: $100.0 Date: " + today + " into account: Saving", a.toString());
        Deposit b = new Deposit(0,today,Customer.CHECKING);
        assertEquals("Deposit of: $0.0 Date: " + today + " into account: Checking", b.toString());
        b = new Deposit(100,today,Customer.CHECKING);
        assertEquals("Deposit of: $100.0 Date: " + today + " into account: Checking", b.toString());
    }
    @Test
    public void testWithdrawString(){
        Withdraw a = new Withdraw(0,today,Customer.SAVING);
        assertEquals("Withdrawal of: $0.0 Date: " + today + " from account: Saving", a.toString());
        a = new Withdraw(100,today,Customer.SAVING);
        assertEquals("Withdrawal of: $100.0 Date: " + today + " from account: Saving", a.toString());
        Withdraw b = new Withdraw(0,today,Customer.CHECKING);
        assertEquals("Withdrawal of: $0.0 Date: " + today + " from account: Checking", b.toString());
        b = new Withdraw(100,today,Customer.CHECKING);
        assertEquals("Withdrawal of: $100.0 Date: " + today + " from account: Checking", b.toString());
    }
}
