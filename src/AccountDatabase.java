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
            if (accounts[i].equals(account)) return i;
        }
        return NOT_FOUND;
    }

    private void grow() {
        Account [] grown = new Account[accounts.length + 4];
        System.arraycopy(accounts, 0, grown, 0, accounts.length);
        this.accounts = grown;
    }

    public boolean open(Account account) {
        int index = find(account);
        if (index != NOT_FOUND) {
            if (accounts[index].closed) {
                accounts[index].changeState();
                accounts[index].deposit(account.balance);
                if (accounts[index].getType().equals("College Checking")) {
                    ((CollegeChecking) accounts[index]).changeCampus(((CollegeChecking) accounts[index]).getCode());
                }

                if (accounts[index].getType().equals("Savings")) {
                    ((Savings) accounts[index]).changeLoyalty(((Savings) accounts[index]).getLoyalty());
                }

                if(accounts[index].getType().equals("Money Market Savings")) {
                    ((MoneyMarket) accounts[index]).changeLoyalty(1);
                }
                System.out.println("Account reopened.");
                return true;
            }
        }

        else {
            if (accounts[accounts.length - 1] != null) grow();
            accounts[numAcct] = account;
            numAcct++;
            System.out.println("Account opened.");
            return true;
        }
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
        System.out.println("*list of accounts in the database*");
        for (int i = 0; i < numAcct; i++) {
            System.out.println(accounts[i]);
        }
        System.out.println("*end of list*");
    }

    public void sortType(Account[] accounts) {
        for (int i = 0; i < numAcct; i++) {
            for (int j = i + 1; j < numAcct; j++) {
                if (!accounts[j].equals(null)
                    && accounts[j].getType().compareTo(accounts[i].getType()) < 0) {
                    Account temp = accounts[j];
                    accounts[j] = accounts[i];
                    accounts[i] = temp;
                }
            }
        }
    }

    public void printByAccountType() {
        System.out.println("*list of accounts by account type.");
        sortType(accounts);
        for (int i = 0; i < numAcct; i++) {
            System.out.println(accounts[i]);
        }
        System.out.println("*end of list.");
    }

    public void printFeeAndInterest() {

    }
}
