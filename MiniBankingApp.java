import java.util.Scanner;

public class MiniBankingApp {
    private static final Bank bank = new Bank();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Display Accounts");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    createAccount(scanner);
                    break;
                case 2:
                    performDeposit(scanner);
                    break;
                case 3:
                    performWithdraw(scanner);
                    break;
                case 4:
                    displayAccounts();
                    break;
                case 5:
                    System.out.println("Exiting the application. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void createAccount(Scanner scanner) {
        System.out.print("Enter account number: ");
        int accountNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter customer name: ");
        String customerName = scanner.nextLine();

        System.out.print("Enter initial balance: $");
        double balance = scanner.nextDouble();

        Account account = new Account(accountNumber, customerName, balance);
        bank.addAccount(account);
        System.out.println("Account created successfully!");
    }

    private static void performDeposit(Scanner scanner) {
        System.out.print("Enter account number: ");
        int accountNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Account account = findAccount(accountNumber);

        if (account != null) {
            System.out.print("Enter deposit amount: $");
            double amount = scanner.nextDouble();
            account.deposit(amount);
        } else {
            System.out.println("Account not found. Please enter a valid account number.");
        }
    }

    private static void performWithdraw(Scanner scanner) {
        System.out.print("Enter account number: ");
        int accountNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Account account = findAccount(accountNumber);

        if (account != null) {
            System.out.print("Enter withdrawal amount: $");
            double amount = scanner.nextDouble();
            account.withdraw(amount);
        } else {
            System.out.println("Account not found. Please enter a valid account number.");
        }
    }

    private static void displayAccounts() {
        System.out.println("Accounts:");
        for (Account account : bank.getAccounts()) {
            System.out.println(account);
        }
    }

    private static Account findAccount(int accountNumber) {
        for (Account account : bank.getAccounts()) {
            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }
        return null;
    }
}
