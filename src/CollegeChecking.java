/**
 * A college checking account sub-class of the Account class
 * @author Rory Xu, Hassan Alfareed
 */
public class CollegeChecking extends Account {

    private int campusCode;

    public CollegeChecking(Profile holder, double balance, int campusCode) {
        super(holder, balance);
        this.campusCode = campusCode;
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
