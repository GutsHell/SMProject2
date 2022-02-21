public class Checking extends Account {

    public Checking(Profile holder, double balance) {
        super(holder, balance);
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
