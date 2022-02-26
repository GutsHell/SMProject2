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

    public void changeLoyalty(int loyalty) {
        this.loyalty = loyalty;
    }

    public int getLoyalty() {
        return loyalty;
    }

    public String isLoyal() {
        return loyalty == 1 ? "::Loyal" : "";
    }

    @Override
    public String toString() {
        return closed == false
                ? this.getType() + "::" + this.holder.toString() + "::Balance $" + df.format(this.balance) + this.isLoyal()
                : this.getType() + "::" + this.holder.toString() + "::Balance $" + df.format(this.balance) + "::CLOSED";
    }

    @Override
    public double monthlyInterest() {
        return loyalty == 1 ? 0.0045/12 : 0.003/12;
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
