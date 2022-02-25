/**
 * A savings account sub-class of the Account class
 * @author Rory Xu, Hassan Alfareed
 */
public class Savings extends Account {

    private int loyalty;

    public Savings(Profile holder, double balance, int loyalty) {
        super(holder, balance);
        this.loyalty = loyalty;
    }

    @Override
    public double monthlyInterest() {
        return this.loyalty == 1 ? 0.045/12 : 0.03/12;
    }

    @Override
    public double fee() {
        return this.balance >= 300 ? 0 : 6;
    }

    @Override
    public String getType() {
        return "Savings";
    }
}
