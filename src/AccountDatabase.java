/**
 * A database that holds all created account information
 * @author Rory Xu, Hassan Alfareed
 */
public class AccountDatabase {
    private Account [] accounts;
    private int numAcct;
    static int NOT_FOUND = -1;

    public AccountDatabase() {
        this.accounts = new Account[4];
        numAcct = 0;
    }

    private int find(Account account) {
        for (int i = 0; i < numAcct; i++) {
            if (account.equals(accounts[i])) return i;
        }
        return NOT_FOUND;
    }

    private void grow() {
        Account [] grown = new Account[accounts.length + 4];
        System.arraycopy(accounts, 0, grown, 0, accounts.length);
        this.accounts = grown;
    }

    public boolean open(Account account) {
        return false;
    }

    public boolean close(Account account) {
        return false;
    }

    public void deposit(Account account) {

    }

    public boolean withdraw(Account account) {
        return false;
    }

    public void print() {

    }

    public void printByAccountType() {

    }

    public void printFeeAndInterest() {

    }
}
