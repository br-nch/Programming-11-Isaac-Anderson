import javax.security.sasl.SaslClient;
import java.util.ArrayList;
import java.util.Date;

public class Customer {
    private int accountNumber;
    private ArrayList<Deposit> deposits;
    private ArrayList<Withdraw> withdraws;
    private double checkBalance;
    private double savingBalance;
    private double savingRate;
    private String name;
    public static final String CHECKING = "Checking";
    public static final String SAVING = "Saving";
    private final int OVERDRAFT = -100;

    Customer(){
        this.accountNumber = 0;
        this.deposits = new ArrayList<>();
        this.withdraws = new ArrayList<>();
        this.checkBalance = 0;
        this.savingBalance = 0;
        this.name = "";
    }

    Customer(String name, int accountNumber, double checkDeposit, double savingDeposit){
        this.accountNumber = accountNumber;
        this.deposits = new ArrayList<>();
        this.withdraws = new ArrayList<>();
        this.checkBalance = checkDeposit;
        this.savingBalance = savingDeposit;
        this.name = name;
    }

    //Requires: amt >= 0, account == "Checking" or "Saving"
    //Modifies: this, deposits, balance
    //Effects: Adds the deposit to the named account and keeps a log of it in the deposits ArrayList
    public double deposit(double amt, Date date, String account){
        this.deposits.add(new Deposit(amt, date, account));
        if (account.equals("Saving")) {
            this.savingBalance += amt;
            return this.savingBalance;
        }
        else {
            this.checkBalance += amt;
            return this.checkBalance;
        }
    }
    //Requires: amt >= 0, account == CHECKING or SAVING
    //Modifies: this, deposits, balance
    //Effects: Withdraws the amount from the named account and logs it
    public double withdraw(double amt, Date date, String account){
        if (checkOverdraft(amt, account) && account.equals("Saving")) {
            System.out.println("Unable to go past your overdraft, withdrew only $" + (this.savingBalance - OVERDRAFT));
            this.withdraws.add(new Withdraw(this.savingBalance - OVERDRAFT, date, account));
            this.savingBalance = OVERDRAFT;
            return savingBalance;
        }
        else if (checkOverdraft(amt, account) && account.equals("Checking")) {
            System.out.println("Unable to go past your overdraft, withdrew only $" + (this.checkBalance - OVERDRAFT));
            this.withdraws.add(new Withdraw(this.checkBalance - OVERDRAFT, date, account));
            this.checkBalance = OVERDRAFT;
            return checkBalance;
        }
        else if (account.equals("Saving")) {
            this.withdraws.add(new Withdraw(amt, date, account));
            this.savingBalance -= amt;
            return this.savingBalance;
        }
        else {
            this.withdraws.add(new Withdraw(amt, date, account));
            this.checkBalance -= amt;
            return this.checkBalance;
        }
    }
    private boolean checkOverdraft(double amt, String account){
        if ((account.equals("Checking") && this.checkBalance - amt < OVERDRAFT) || (account.equals("Saving") && this.savingBalance - amt < OVERDRAFT)) return true;
        else return false;
    }
    //do not modify
    public void displayDeposits(){
        for(Deposit d : deposits){
            System.out.println(d);
        }
    }
    //do not modify
    public void displayWithdraws(){
        for(Withdraw w : withdraws){
            System.out.println(w);
        }
    }

}
