/**
 * A generalized account class to allow for inheritance of different account types
 * Includes account holder information, account state, and account balance
 * @author Rory Xu, Hassan Alfareed
 */

public abstract class Account {
    protected Profile holder;
    protected boolean closed = false;
    protected double balance;

    public Account(Profile holder, double balance) {
        this.holder = holder;
        this.balance = balance;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Account other))
            return false;
        return this.holder.equals(other.holder) && this.getType().equals(other.getType());
    }

    @Override
    public String toString() {
        return "";
    }

    public void withdraw(double amount) {

    }
    public void deposit(double amount) {

    }

    public void changeState() {

    }

    public abstract double monthlyInterest();
    public abstract double fee();
    public abstract String getType();
}
