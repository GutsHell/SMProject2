/**
 * A database that holds all created account information
 * @author Rory Xu, Hassan Alfareed
 */
public class AccountDatabase {
    private Account [] accounts;
    private int numAcct;
    static int NOT_FOUND = -1;
    static int HAS_CHECKING = -2;

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

    private int findChecking(Account account) {
        for (int i = 0; i < numAcct; i++) {
            if (accounts[i].getHolder().equals(account.getHolder())
                    && ((accounts[i].getType().equals("Checking") && account.getType().equals("College Checking"))
                || (accounts[i].getType().equals("College Checking") && account.getType().equals("Checking")))) {
                return HAS_CHECKING;
            }
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
        int hasChecking = findChecking(account);
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
            if (hasChecking == HAS_CHECKING) {
                return false;
            }
            if (accounts[accounts.length - 1] != null) grow();
            accounts[numAcct] = account;
            numAcct++;
            System.out.println("Account opened.");
            return true;
        }
        return false;
    }

    public boolean close(Account account) {
        int index = find(account);
        if (index != NOT_FOUND) {
            if (!accounts[index].closed) {
                accounts[index].changeState();
                accounts[index].setBalance(0);
                if (accounts[index].getType().equals("Savings")) {
                    ((Savings) accounts[index]).changeLoyalty(0);
                }

                if(accounts[index].getType().equals("Money Market Savings")) {
                    ((MoneyMarket) accounts[index]).changeLoyalty(0);
                }
                System.out.println("Account closed.");
                return true;
            }
        }
        return false;
    }

    public void deposit(Account account) {
        if (account.balance <= 0) {
            System.out.println("Deposit - amount cannot be 0 or negative");
            return;
        }
        int index = find(account);
        if (index != NOT_FOUND) {
            accounts[index].deposit(account.balance);
            System.out.println("Deposit - balance updated.");
        }
        else {
            System.out.println(account.holder + " " + account.getType() + " is not in the database.");
        }
    }

    public boolean withdraw(Account account) {
        if (account.balance <= 0) {
            System.out.println("Withdraw - amount cannot be 0 or negative");
            return false;
        }
        int index = find(account);
        if (index == NOT_FOUND) {
            System.out.println(account.holder + " " + account.getType() + " is not in the database.");
            return false;
        }
        else {
            if (accounts[index].balance < account.balance) {
                System.out.println("Withdraw - insufficient funds.");
                return false;
            }
        }
        accounts[index].withdraw(account.balance);
        if (accounts[index].getType().equals("Money Market Savings")) {
            ((MoneyMarket) accounts[index]).addWithdrawal();
            if (accounts[index].balance < 2500) {
                ((MoneyMarket) accounts[index]).changeLoyalty(0);
            }
        }
        System.out.println("Withdraw - balance updated.");
        return true;
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
