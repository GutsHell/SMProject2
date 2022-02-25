/**
 * The profile
 */
public class Profile {
    private String fname;
    private String lname;
    private Date dob;

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Profile other))
            return false;
        return this.fname.equals(other.fname)
                && this.lname.equals(other.lname)
                && this.dob.equals(other.dob);
    }

    @Override
    public String toString() {
        return "";
    }
}
