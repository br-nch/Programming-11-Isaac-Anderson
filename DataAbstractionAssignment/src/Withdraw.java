import java.util.Date;

public class Withdraw {
    private double amount;
    private Date date;
    private String account;

    Withdraw(double amount, Date date, String account){
        this.amount = amount;
        this.date = date;
        this.account = account;
    }

    //Requires: Valid Withdrawal
    //Effects: Displays information about the withdrawal as a string
    public String toString(){
        return "Withdrawal of: $" + amount + " Date: " + date + " from account: " + account;
    }
}
