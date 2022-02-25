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

    public String getCampus() {
        if (campusCode == 0) return "NEW_BRUNSWICK";
        return campusCode == 1 ? "NEWARK" : "CAMDEN";
    }

    @Override
    public String toString() {
        return closed == false
                ? this.getType() + "::" + this.holder.toString() + "::Balance $" + this.balance + "::" + this.getCampus()
                : this.getType() + "::" + this.holder.toString() + "::Balance $" + this.balance + "::CLOSED::" + this.getCampus();
    }

    @Override
    public double monthlyInterest() {
        return 0.025/12;
    }

    @Override
    public double fee() {
        return 0;
    }

    @Override
    public String getType() {
        return "College Checking";
    }
}
