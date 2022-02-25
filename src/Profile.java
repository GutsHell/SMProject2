/**
 * The profile
 */
public class Profile {
    private String fname;
    private String lname;
    private Date dob;

    public Profile(String fname, String lname, Date dob) {
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Profile other))
            return false;
        return this.fname.equalsIgnoreCase(other.fname)
                && this.lname.equalsIgnoreCase(other.lname)
                && this.dob.compareTo(other.dob) == 0;
    }

    @Override
    public String toString() {
        return this.fname + " " + this.lname + " " + this.dob;
    }
}
