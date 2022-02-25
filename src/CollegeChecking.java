/**
 * A college checking account sub-class of the Account class
 * @author Rory Xu, Hassan Alfareed
 */
public class CollegeChecking extends Checking {

    private int campusCode;

    public CollegeChecking(Profile holder, double balance, int campusCode) {
        super(holder, balance);
        this.campusCode = campusCode;
    }

    @Override
    public double monthlyInterest() {
        return 0.025/12;
    }

    @Override
    public double fee() {
        return 0;
    }
}
