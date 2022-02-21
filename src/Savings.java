public class Savings extends Account {

    private int loyalty;

    public Savings(Profile holder, double balance, int loyalty) {
        super(holder, balance);
        this.loyalty = loyalty;
    }

    @Override
    public double monthlyInterest() {
        return 0.001;
    }

    @Override
    public double fee() {
        return 0;
    }

    @Override
    public String getType() {
        return null;
    }
}
