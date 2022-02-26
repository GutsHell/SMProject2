import javax.sound.sampled.EnumControl;
import java.util.Scanner;

public class BankTeller {
    public void run() {
        AccountDatabase database = new AccountDatabase();
        Scanner command = new Scanner(System.in);
        boolean online = true;
        System.out.println("Bank Teller is running.");
        while (online) {
            String[] input = command.nextLine().split("\\s+");
            switch (input[0]) {
                case "":
                    System.out.println();
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
                case "UB":  //ubCommand(input);
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

    private static void oCommand(String[] input, AccountDatabase database) {
        try{
            String accType = input[1];
            Date dob = new Date(input[4]);
            if (!dateChecker(dob)) return;
            Profile accHolder = new Profile(input[2], input[3], dob);
            double balance = Double.parseDouble(input[5]);
            if (balance <= 0) {
                System.out.println("Initial deposit cannot be 0 or negative");
                return;
            }
            Account acc = null;
            if (accType.equals("MM")) {
                if (balance < 2500) {
                    System.out.println("Minimum of $2500 to open a MoneyMarket account");
                    return;
                }
                acc = new MoneyMarket(accHolder, balance, 1);
            }
            else if (accType.equals("C")) {
                acc = new Checking(accHolder, balance);
            }
            else if (accType.equals("CC")) {
                if (Integer.parseInt(input[6]) != 0
                        && Integer.parseInt(input[6]) != 1
                        && Integer.parseInt(input[6]) != 2)
                {
                    System.out.println("Invalid campus code.");
                    return;
                }
                acc = new CollegeChecking(accHolder, balance, Integer.parseInt(input[6]));
            }
            else if (accType.equals("S")) {
                if (!(input[6].equals("0") || input[6].equals("1"))) {
                    System.out.println("Invalid loyalty status");
                    return;
                }
                acc = new Savings(accHolder, balance, Integer.parseInt(input[6]));
            }

            if (!database.open(acc)) {
                System.out.println(acc.holder + " same account (type) is in the database.");
            }
        }
        catch (NumberFormatException e) {
            System.out.println("Not a valid amount.");
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid command!");
        }
    }

    private static void cCommand(String[] input, AccountDatabase database) {
        try {
            String accType = input[1];
            Date dob = new Date(input[4]);
            if (!dateChecker(dob)) return;
            Profile accHolder = new Profile(input[2], input[3], dob);

            Account acc = null;
            if (accType.equals("MM")) {
                acc = new MoneyMarket(accHolder, 0, 0);
            }
            else if (accType.equals("C")) {
                acc = new Checking(accHolder, 0);
            }
            else if (accType.equals("CC")) {
                acc = new CollegeChecking(accHolder, 0, 0);
            }
            else if (accType.equals("S")) {
                acc = new Savings(accHolder, 0, 0);
            }
            if (!database.close(acc)) {
                System.out.println("Account is closed already.");
            }
        }

        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Missing data for closing an account.");
        }
    }

    private static void dCommand(String[] input, AccountDatabase database) {
        try {
            String accType = input[1];
            Date dob = new Date(input[4]);
            if (!dateChecker(dob)) return;
            Profile accHolder = new Profile(input[2], input[3], dob);
            double balance = Double.parseDouble(input[5]);

            Account acc = null;
            if (accType.equals("MM")) {
                acc = new MoneyMarket(accHolder, balance, 0);
            }
            else if (accType.equals("C")) {
                acc = new Checking(accHolder, balance);
            }
            else if (accType.equals("CC")) {
                acc = new CollegeChecking(accHolder, balance, 0);
            }
            else if (accType.equals("S")) {
                acc = new Savings(accHolder, balance, 0);
            }
            database.deposit(acc);
        }
        catch (NumberFormatException e) {
            System.out.println("Not a valid amount.");
        }
    }

    private static void wCommand(String[] input, AccountDatabase database) {
        try {
            String accType = input[1];
            Date dob = new Date(input[4]);
            if (!dateChecker(dob)) return;
            Profile accHolder = new Profile(input[2], input[3], dob);
            double balance = Double.parseDouble(input[5]);

            Account acc = null;
            if (accType.equals("MM")) {
                acc = new MoneyMarket(accHolder, balance, 0);
            }
            else if (accType.equals("C")) {
                acc = new Checking(accHolder, balance);
            }
            else if (accType.equals("CC")) {
                acc = new CollegeChecking(accHolder, balance, 0);
            }
            else if (accType.equals("S")) {
                acc = new Savings(accHolder, balance, 0);
            }
            database.withdraw(acc);
        }
        catch (NumberFormatException e) {
            System.out.println("Not a valid amount.");
        }
    }

    private static void pCommand(AccountDatabase database) {
        database.print();
    }

    private static void ptCommand(AccountDatabase database) {
        database.printByAccountType();
    }

    private static void piCommand(AccountDatabase database) {
        database.printFeeAndInterest();
    }

    public static boolean dateChecker(Date dob) {
        Date current = new Date();

        if (!dob.isValid() || dob.compareTo(current) >= 0) {
            System.out.println("Date of birth invalid.");
            return false;

        }
        return true;
    }
}
