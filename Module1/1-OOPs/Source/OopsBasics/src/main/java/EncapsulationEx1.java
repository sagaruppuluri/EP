class Account {

    // Attributes

    // default is zero hence initialization not required.
    private double balance = 0;

    // Operations

    public void debit(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
        }
    }

    public void credit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public void showBalance() {
        System.out.println("Current balance - " + balance);
    }
}

public class EncapsulationEx1 {

    public static void main(String[] args) {
        Account a1;

        a1 = new Account();

        a1.credit(2000);
        a1.debit(1000);

        a1.showBalance();

        // balance is a private field in
        // the class Account, hence
        // directly referring to it through object
        // is invalid.

        // a1.balance = -2000; // invalid

        // debit operation validates the given amount
        // and rejects it if invalid. In the below case
        // because amount is not greater than 0 it won't
        // modify the balance.

        a1.debit(-2000);

        a1.showBalance();
    }

}