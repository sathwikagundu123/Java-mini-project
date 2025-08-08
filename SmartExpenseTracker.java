import java.io.*;
import java.util.*;

class Expense {
    private String category;
    private double amount;
    private String date;
    private String description;

    public Expense(String category, double amount, String date, String description) {
        this.category = category;
        this.amount = amount;
        this.date = date;
        this.description = description;
    }

    public String getCategory() { return category; }
    public double getAmount() { return amount; }
    public String getDate() { return date; }

    @Override
    public String toString() {
        return date + " | " + category + " | Rs." + amount + " | " + description;
    }
}

class ExpenseManager {
    private List<Expense> expenses = new ArrayList<>();

    public void addExpense(Expense e) {
        expenses.add(e);
        saveToFile(e);
    }

    public void viewAllExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses recorded yet.");
        } else {
            expenses.forEach(System.out::println);
        }
    }

    public double calculateTotal() {
        return expenses.stream().mapToDouble(Expense::getAmount).sum();
    }

    private void saveToFile(Expense e) {
        try (FileWriter fw = new FileWriter("expenses.txt", true)) {
            fw.write(e.toString() + "\n");
        } catch (IOException ex) {
            System.out.println("Error saving expense to file.");
        }
    }
}

public class SmartExpenseTracker {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ExpenseManager manager = new ExpenseManager();
        while (true) {
            System.out.println("\n1. Add Expense\n2. View All Expenses\n3. View Total\n4. Exit");
            System.out.print("Choose: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Category: ");
                    String category = sc.nextLine();
                    System.out.print("Amount: ");
                    double amount = sc.nextDouble();
                    sc.nextLine();
                    System.out.print("Date (YYYY-MM-DD): ");
                    String date = sc.nextLine();
                    System.out.print("Description: ");
                    String desc = sc.nextLine();
                    manager.addExpense(new Expense(category, amount, date, desc));
                    break;
                case 2:
                    manager.viewAllExpenses();
                    break;
                case 3:
                    System.out.println("Total Expense: Rs." + manager.calculateTotal());
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
