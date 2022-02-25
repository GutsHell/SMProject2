/**
 * A money market account sub-class of the Account class
 * @author Rory Xu, Hassan Alfareed
 */
public class MoneyMarket extends Account {

    private int loyalty;

    public MoneyMarket(Profile holder, double balance) {
        super(holder, balance);
        this.loyalty = 1;
    }

    @Override
    public double monthlyInterest() {
        return this.loyalty == 1 ? 0.095/12 : 0.08/12;
    }

    @Override
    public double fee() {
        return this.balance > 2500 ? 0 : 10;
    }

    @Override
    public String getType() {
        return "MoneyMarket";
    }
}
