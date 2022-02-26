import java.text.DecimalFormat;

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

    protected static final DecimalFormat df = new DecimalFormat("0.00");

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Account other))
            return false;
        return this.holder.equals(other.holder) && this.getType().equals(other.getType());
    }

    @Override
    public String toString() {
        return closed == false
                ? this.getType() + "::" + this.holder.toString() + "::Balance $" + df.format(this.balance)
                : this.getType() + "::" + this.holder.toString() + "::Balance $" + df.format(this.balance) + "::CLOSED";
    }

    public void withdraw(double amount) {
        balance -= amount;
    }
    public void deposit(double amount) {
        balance += amount;
    }

    public void changeState() {
        closed = !closed;
    }

    public void setBalance(int newBal) {
        balance = newBal;
    }

    public Profile getHolder() {
        return holder;
    }

    public abstract double monthlyInterest();
    public abstract double fee();
    public abstract String getType();
}
