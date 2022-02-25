/**
 * A money market account sub-class of the Account class
 * @author Rory Xu, Hassan Alfareed
 */
public class MoneyMarket extends Savings {

    private int withdrawals;

    public MoneyMarket(Profile holder, double balance, int loyalty) {
        super(holder, balance, loyalty);
        this.withdrawals = 0;
    }

    public void addWithdrawal() {
        this.withdrawals++;
    }

    public int getWithdrawals() {
        return withdrawals;
    }

    @Override
    public String toString() {
        return closed == false
                ? this.getType() + "::" + this.holder.toString()
                    + "::Balance $" + df.format(this.balance) + this.isLoyal() + "::Withdrawals: " + getWithdrawals()
                : this.getType() + "::" + this.holder.toString()
                    + "::Balance $" + df.format(this.balance) + "::CLOSED::Withdrawals: " + getWithdrawals();
    }

    @Override
    public double monthlyInterest() {
        return this.loyalty == 1 ? 0.095/12 : 0.08/12;
    }

    @Override
    public double fee() {
        if (withdrawals > 3) return 10;
        return this.balance > 2500 ? 0 : 10;
    }

    @Override
    public String getType() {
        return "Money Market Savings";
    }
}
