package bankteller;

import java.text.DecimalFormat;

/**
 * A database that holds all created account information
 * @author Rory Xu, Hassan Alfareed
 */
public class AccountDatabase {
    private Account [] accounts;
    private int numAcct;
    private static final int NOT_FOUND = -1;
    private static final int HAS_CHECKING = -2;
    private static final DecimalFormat df = new DecimalFormat("#,##0.00");

    /**
     * Constructs a new account database with a starting size of 4 accounts
     */
    public AccountDatabase() {
        this.accounts = new Account[4];
        numAcct = 0;
    }

    /**
     * Finds an account with the same type and holder information
     * @param account The account to be looked for
     * @return The index of the account if found, otherwise NOT_FOUND = -1
     */
    private int find(Account account) {
        for (int i = 0; i < numAcct; i++) {
            if (accounts[i].equals(account)) return i;
        }
        return NOT_FOUND;
    }

    /**
     * Checks whether a holder already owns a checking account of either type
     * @param account The account that the holder wishes to open
     * @return The index of an already existing checking type account, or NOT_FOUND = -1
     */
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

    /**
     * Grows the size of the database by 4 should a new addition surpass the limit of the current array
     */
    private void grow() {
        Account [] grown = new Account[accounts.length + 4];
        System.arraycopy(accounts, 0, grown, 0, accounts.length);
        this.accounts = grown;
    }

    /**
     * Opens an account in the database
     * Can reopen closed accounts
     * @param account The account to be opened
     * @return True if the opening was successful, false if it was not
     */
    public boolean open(Account account) {
        int index = find(account);
        int hasChecking = findChecking(account);
        if (index != NOT_FOUND) {
            if (accounts[index].closed) {
                accounts[index].changeState();
                accounts[index].deposit(account.balance);
                if (accounts[index].getType().equals("College Checking")) {
                    ((CollegeChecking) accounts[index]).changeCampus(((CollegeChecking) account).getCode());
                }

                if (accounts[index].getType().equals("Savings")) {
                    ((Savings) accounts[index]).changeLoyalty(((Savings) account).getLoyalty());
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

    /**
     * Closes an account in the database
     * @param account The account to be closed
     * @return True if the account was successfully closed, false if not
     */
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
                    ((MoneyMarket) accounts[index]).resetWithdrawals();
                }
                System.out.println("Account closed.");
                return true;
            }
        }
        return false;
    }

    /**
     * Deposits money into an account in the database
     * @param account The account to deposit money into
     */
    public void deposit(Account account) {
        if (account.balance <= 0) {
            System.out.println("Deposit - amount cannot be 0 or negative.");
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

    /**
     * Withdraws money from an account in the database
     * @param account The account to deposit money into
     * @return True if the withdrawal was successful, false if not
     */
    public boolean withdraw(Account account) {
        if (account.balance <= 0) {
            System.out.println("Withdraw - amount cannot be 0 or negative.");
            return false;
        }
        int index = find(account);
        if (index == NOT_FOUND) {
            System.out.println(account.holder + " " + account.getType() + " is not in the database.");
            return false;
        }
        else {
            if (accounts[index].balance < account.balance) {
                System.out.println("Withdraw - insufficient fund.");
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
        return true;
    }

    /**
     * Prints the database in current order
     */
    public void print() {

        if (!(numAcct > 0)) {
            System.out.println("Account Database is empty!");
            return;
        }
        System.out.println("\n*list of accounts in the database*");
        for (int i = 0; i < numAcct; i++) {
            System.out.println(accounts[i]);
        }
        System.out.println("*end of list*\n");
    }

    /**
     * Sorts the accounts in the database alphabetically by type
     * @param accounts The list of accounts
     */
    public void sortType(Account[] accounts) {
        for (int i = 0; i < numAcct; i++) {
            for (int j = i + 1; j < numAcct; j++) {
                if (accounts[j] != null
                    && accounts[j].getType().compareTo(accounts[i].getType()) < 0) {
                    Account temp = accounts[j];
                    accounts[j] = accounts[i];
                    accounts[i] = temp;
                }
            }
        }
    }

    /**
     * Prints the database in alphabetical order by type
     */
    public void printByAccountType() {
        if (!(numAcct > 0)) {
            System.out.println("Account Database is empty!");
            return;
        }
        System.out.println("\n*list of accounts by account type.");
        sortType(accounts);
        for (int i = 0; i < numAcct; i++) {
            System.out.println(accounts[i]);
        }
        System.out.println("*end of list.\n");
    }

    /**
     * Prints the database in current order and with monthly fee and interest information
     */
    public void printFeeAndInterest() {
        if (!(numAcct > 0)) {
            System.out.println("Account Database is empty!");
            return;
        }
        System.out.println("\n*list of accounts with fee and monthly interest");
        for (int i = 0; i < numAcct; i++) {
            System.out.println(accounts[i] + "::fee $" + df.format(accounts[i].fee())
                    + "::monthly interest $"
                    + df.format(accounts[i].balance*accounts[i].monthlyInterest()));
        }
        System.out.println("*end of list.\n");
    }

    /**
     * Updates the account balances with fee and interest rates
     */
    public void update() {
        if (!(numAcct > 0)) {
            System.out.println("Account Database is empty!");
            return;
        }
        System.out.println("\n*list of accounts with updated balance");
        for (int i = 0; i < numAcct; i++) {
            accounts[i].deposit(accounts[i].balance*accounts[i].monthlyInterest());
            accounts[i].withdraw(accounts[i].fee());
            System.out.println(accounts[i]);
        }
        System.out.println("*end of list.\n");
    }
}
