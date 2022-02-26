package bankteller;

import java.util.Scanner;

/**
 * A user interface class that allows for input and returns output in the console,
 * acting as a virtual bank teller
 * @author Rory Xu, Hassan Alfareed
 */
public class BankTeller {
    /**
     * Takes console input and produces console output
     */
    public void run() {
        AccountDatabase database = new AccountDatabase();
        Scanner command = new Scanner(System.in);
        boolean online = true;
        System.out.println("Bank Teller is running.");
        while (online) {
            String[] input = command.nextLine().split("\\s+");
            switch (input[0]) {
                case "":
                    break;
                case "O":
                    oCommand(input, database);
                    break;
                case "C":
                    cCommand(input, database);
                    break;
                case "D":
                    dCommand(input, database);
                    break;
                case "W":
                    wCommand(input, database);
                    break;
                case "P":
                    pCommand(database);
                    break;
                case "PT":
                    ptCommand(database);
                    break;
                case "PI":
                    piCommand(database);
                    break;
                case "UB":
                    ubCommand(database);
                    break;
                case "Q":
                    online = false;
                    System.out.println("Bank Teller is terminated.");
                    break;
                default:
                    System.out.println("Invalid command!");
                    break;
            }
        }
        command.close();
    }

    /**
     * Executes when the teller wishes to open a new account
     * Can also open closed accounts
     * @param input Open account information
     * @param database The database of accounts to be modified
     */
    private static void oCommand(String[] input, AccountDatabase database) {
        try{
            String accType = input[1];
            Date dob = new Date(input[4]);
            if (dateChecker(dob)) return;
            Profile accHolder = new Profile(input[2], input[3], dob);
            double balance = Double.parseDouble(input[5]);
            if (balance <= 0) {
                System.out.println("Initial deposit cannot be 0 or negative");
                return;
            }
            Account acc = null;
            switch (accType) {
                case "MM" -> {
                    if (balance < 2500) {
                        System.out.println("Minimum of $2500 to open a MoneyMarket account");
                        return;
                    }
                    acc = new MoneyMarket(accHolder, balance, 1);
                }
                case "C" -> acc = new Checking(accHolder, balance);
                case "CC" -> {
                    if (Integer.parseInt(input[6]) != 0
                            && Integer.parseInt(input[6]) != 1
                            && Integer.parseInt(input[6]) != 2) {
                        System.out.println("Invalid campus code.");
                        return;
                    }
                    acc = new CollegeChecking(accHolder, balance, Integer.parseInt(input[6]));
                }
                case "S" -> {
                    if (!(input[6].equals("0") || input[6].equals("1"))) {
                        System.out.println("Invalid loyalty status");
                        return;
                    }
                    acc = new Savings(accHolder, balance, Integer.parseInt(input[6]));
                }
            }

            if (!database.open(acc)) {
                assert acc != null;
                System.out.println(acc.holder + " same account (type) is in the database.");
            }
        }
        catch (NumberFormatException e) {
            System.out.println("Not a valid amount.");
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Missing data for opening an account.");
        }
    }

    /**
     * Executes when the teller wishes to close an account
     * @param input Close account information
     * @param database The database of accounts to be modified
     */
    private static void cCommand(String[] input, AccountDatabase database) {
        try {
            String accType = input[1];
            Date dob = new Date(input[4]);
            if (dateChecker(dob)) return;
            Profile accHolder = new Profile(input[2], input[3], dob);

            Account acc = switch (accType) {
                case "MM" -> new MoneyMarket(accHolder, 0, 0);
                case "C" -> new Checking(accHolder, 0);
                case "CC" -> new CollegeChecking(accHolder, 0, 0);
                case "S" -> new Savings(accHolder, 0, 0);
                default -> null;
            };
            if (!database.close(acc)) {
                System.out.println("Account is closed already.");
            }
        }

        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Missing data for closing an account.");
        }
    }

    /**
     * Executes when the teller wishes to deposit money into an account
     * @param input Deposit information
     * @param database The database of accounts to be modified
     */
    private static void dCommand(String[] input, AccountDatabase database) {
        try {
            String accType = input[1];
            Date dob = new Date(input[4]);
            if (dateChecker(dob)) return;
            Profile accHolder = new Profile(input[2], input[3], dob);
            double balance = Double.parseDouble(input[5]);

            Account acc = switch (accType) {
                case "MM" -> new MoneyMarket(accHolder, balance, 0);
                case "C" -> new Checking(accHolder, balance);
                case "CC" -> new CollegeChecking(accHolder, balance, 0);
                case "S" -> new Savings(accHolder, balance, 0);
                default -> null;
            };
            assert acc != null;
            database.deposit(acc);
        }
        catch (NumberFormatException e) {
            System.out.println("Not a valid amount.");
        }
    }

    /**
     * Executes when the teller wishes to withdraw money from an account
     * Cannot overdraw an account into negatives
     * @param input Withdrawal information
     * @param database The database of accounts to be modified
     */
    private static void wCommand(String[] input, AccountDatabase database) {
        try {
            String accType = input[1];
            Date dob = new Date(input[4]);
            if (dateChecker(dob)) return;
            Profile accHolder = new Profile(input[2], input[3], dob);
            double balance = Double.parseDouble(input[5]);

            Account acc = switch (accType) {
                case "MM" -> new MoneyMarket(accHolder, balance, 0);
                case "C" -> new Checking(accHolder, balance);
                case "CC" -> new CollegeChecking(accHolder, balance, 0);
                case "S" -> new Savings(accHolder, balance, 0);
                default -> null;
            };
            assert acc != null;
            if (database.withdraw(acc))
                System.out.println("Withdraw - balance updated.");
        }
        catch (NumberFormatException e) {
            System.out.println("Not a valid amount.");
        }
    }

    /**
     * Prints the list of accounts in the database in current order
     * @param database The database of accounts to print
     */
    private static void pCommand(AccountDatabase database) {
        database.print();
    }

    /**
     * Prints the list of acocunts in the database ordered by the type, alphabetically
     * @param database The database of accounts to print
     */
    private static void ptCommand(AccountDatabase database) {
        database.printByAccountType();
    }

    /**
     * Prints the list of accounts in the database and includes their monthly fee and interest information
     * @param database The database of accounts to print
     */
    private static void piCommand(AccountDatabase database) {
        database.printFeeAndInterest();
    }

    /**
     * Prints the list of accounts in the database with updated values using monthly fee and interest application
     * @param database The database of accounts to print
     */
    private static void ubCommand(AccountDatabase database) {
        database.update();
    }

    /**
     * Checks whether or not the date of birth is a valid date of birth
     * @param dob The date of birth to be checked
     * @return Whether or not the date of birth is valid
     */
    public static boolean dateChecker(Date dob) {
        Date current = new Date();

        if (!dob.isValid() || dob.compareTo(current) >= 0) {
            System.out.println("Date of birth invalid.");
            return true;

        }
        return false;
    }
}
