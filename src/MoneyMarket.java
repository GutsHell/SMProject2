/**
 * A money market account sub-class of the Account class
 * @author Rory Xu, Hassan Alfareed
 */
public class MoneyMarket extends Savings {

    private int withdrawals;

    /**
     * Constructs a Money Market account
     * Uses Savings' super, but also sets the number of withdrawals to 0
     * @param holder
     * @param balance
     * @param loyalty
     */
    public MoneyMarket(Profile holder, double balance, int loyalty) {
        super(holder, balance, loyalty);
        this.withdrawals = 0;
    }

    /**
     * Increments the number of withdrawals by 1
     */
    public void addWithdrawal() {
        this.withdrawals++;
    }

    /**
     * Gets the number of withdrawals this acocunt has
     * @return
     */
    public int getWithdrawals() {
        return withdrawals;
    }

    /**
     * Overrides the Savings' toString in order display important information for a money market account
     * @return This money market account's information
     */
    @Override
    public String toString() {
        return closed == false
                ? this.getType() + "::" + this.holder.toString()
                    + "::Balance $" + df.format(this.balance) + this.isLoyal() + "::Withdrawals: " + getWithdrawals()
                : this.getType() + "::" + this.holder.toString()
                    + "::Balance $" + df.format(this.balance) + "::CLOSED::Withdrawals: " + getWithdrawals();
    }

    /**
     * Overrides the abstract method in Savings.java for money market account specifics
     * @return The interest rate of the money market account
     */
    @Override
    public double monthlyInterest() {
        return this.loyalty == 1 ? 0.0095/12 : 0.008/12;
    }

    /**
     * Overrides the abstract method in Savings.java for money market account specifics
     * @return The monthly fee of the money market account
     */
    @Override
    public double fee() {
        if (withdrawals > 3) return 10;
        return this.balance > 2500 ? 0 : 10;
    }

    /**
     * Overrides the abstract method in Savings.java for money market account specifics
     * @return This account's type
     */
    @Override
    public String getType() {
        return "Money Market Savings";
    }
}
