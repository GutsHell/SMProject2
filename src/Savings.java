/**
 * A savings account sub-class of the Account class
 * @author Rory Xu, Hassan Alfareed
 */
public class Savings extends Account {

    protected int loyalty;

    public Savings(Profile holder, double balance, int loyalty) {
        super(holder, balance);
        this.loyalty = loyalty;
    }

    public void changeLoyalty() {
        if (loyalty == 1) loyalty = 0;
        else loyalty = 1;
    }

    public String getLoyalty() {
        return loyalty == 1 ? "::Loyal" : "";
    }

    @Override
    public String toString() {
        return closed == false
                ? this.getType() + "::" + this.holder.toString() + "::Balance $" + this.balance + this.getLoyalty()
                : this.getType() + "::" + this.holder.toString() + "::Balance $" + this.balance + "::CLOSED";
    }

    @Override
    public double monthlyInterest() {
        return loyalty == 1 ? 0.045/12 : 0.03/12;
    }

    @Override
    public double fee() {
        return balance >= 300 ? 0 : 6;
    }

    @Override
    public String getType() {
        return "Savings";
    }
}
