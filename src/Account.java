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
        return false;
    }

    @Override
    public String toString() {
        return "";
    }

    public void withdraw(double amount) {

    }
    public void deposit(double amount) {

    }

    public abstract double monthlyInterest();
    public abstract double fee();
    public abstract String getType();
}
