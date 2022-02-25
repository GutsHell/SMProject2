/**
 * A checking account sub-class of the Account class
 * @author Rory Xu, Hassan Alfareed
 */
public class Checking extends Account {

    public Checking(Profile holder, double balance) {
        super(holder, balance);
    }

    @Override
    public double monthlyInterest() {
        return 0.001/12;
    }

    @Override
    public double fee() {
        if (this.balance >= 1000)
            return 0;
        else
            return 25;
    }

    @Override
    public String getType() {
        return "Checking";
    }
}
