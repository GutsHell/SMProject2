import java.util.Scanner;

public class BankTeller {
    public void run() {
        AccountDatabase database = new AccountDatabase();
        Scanner command = new Scanner(System.in);
        boolean online = true;
        System.out.print("Bank Teller is running.\n");
        while (online) {
            String[] inputAsArray = command.nextLine().split(" ");
            switch (inputAsArray[0]) {
                case "O":
                    //oCommand(inputAsArray, database);
                    break;
                case "C":
                    //cCommand(inputAsArray, database);
                    break;
                case "D":
                    //dCommand(inputAsArray, database);
                    break;
                case "W":
                    //wCommand(inputAsArray, database);
                    break;
                case "P":  //pCommand(inputAsArray);
                    break;
                case "PT":  //ptCommand(inputAsArray);
                    break;
                case "PI":  //piCommand(inputAsArray);
                    break;
                case "UB":  //ubCommand(inputAsArray);
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
}
