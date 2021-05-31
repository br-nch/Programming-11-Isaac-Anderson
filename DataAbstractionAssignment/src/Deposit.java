import java.util.Date;

public class Deposit {
    private double amount;
    private Date date;
    private String account;

    Deposit(double amount, Date date, String account){
        this.amount = amount;
        this.date = date;
        this.account = account;
    }

    //Requires: Valid Deposit
    //Effects: Displays information about the deposit as a string
    public String toString(){
        return "Deposit of: $" + amount + " Date: " + date + " into account: " + account;
    }
}
