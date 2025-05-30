package LibrarySystem;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library lib = new Library();
        UserManager um = new UserManager();
        Scanner sc = new Scanner(System.in);


        lib.loadBooksFromExcel("books.xlsx");
        um.loadUsersFromExcel("students.xlsx", "student");
        um.loadUsersFromExcel("teachers.xlsx", "teacher");

        System.out.println("Welcome to East West University Library!\n");

        User me = null;
        while (me == null) {
            System.out.println("Are you a Student or a Teacher?");
            System.out.println("1. Student");
            System.out.println("2. Teacher");
            System.out.println("3. Exit");
            System.out.print("Choice: ");

            if (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine();
                continue;
            }

            int roleChoice = sc.nextInt();
            sc.nextLine();

            if (roleChoice == 3) {
                System.out.println("Goodbye!");
                sc.close();
                return;
            }

            String userType = (roleChoice == 1) ? "student" : "teacher";
            if (roleChoice != 1 && roleChoice != 2) {
                System.out.println("Invalid choice. Please select 1 or 2.");
                continue;
            }

            System.out.print("Enter your " + userType + " ID: ");
            if (!sc.hasNextInt()) {
                System.out.println("Invalid ID format. Please enter numbers only.");
                sc.nextLine();
                continue;
            }

            int userId = sc.nextInt();
            sc.nextLine();

            try {
                me = um.getUserById(userId);
                System.out.println("\nWelcome, " + me + "!");
            } catch (UserNotFoundException e) {
                System.err.println(e.getMessage());
                System.out.println("Please try again.\n");
            }
        }

        boolean running = true;
        while (running) {
            System.out.println("\nWhat would you like to do?");
            System.out.println("1. View available books");
            System.out.println("2. Borrow a book");
            System.out.println("3. Return a book");
            System.out.println("4. View my borrowed books");
            System.out.println("5. Exit");
            System.out.print("Choice: ");

            if (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine();
                continue;
            }

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    lib.displayAvailableBooks();
                    break;
                case 2:
                    System.out.print("Enter Book ID to borrow: ");
                    if (!sc.hasNextInt()) {
                        System.out.println("Invalid book ID!");
                        sc.nextLine();
                        break;
                    }
                    int bid = sc.nextInt();
                    sc.nextLine();
                    System.out.print("How many copies? ");
                    if (!sc.hasNextInt()) {
                        System.out.println("Invalid quantity!");
                        sc.nextLine();
                        break;
                    }
                    int qty = sc.nextInt();
                    sc.nextLine();
                    try {
                        Book b = lib.getBookById(bid);
                        me.borrowBook(b, qty);
                    } catch (Exception e) {
                        System.err.println("Error: " + e.getMessage());
                    }
                    break;
                case 3:
                    System.out.print("Enter Book ID to return: ");
                    if (!sc.hasNextInt()) {
                        System.out.println("Invalid book ID!");
                        sc.nextLine();
                        break;
                    }
                    int rid = sc.nextInt();
                    sc.nextLine();
                    try {
                        Book rb = lib.getBookById(rid);
                        me.returnBook(rb);
                    } catch (Exception e) {
                        System.err.println("Error: " + e.getMessage());
                    }
                    break;
                case 4:
                    me.viewBorrowedBooks();
                    break;
                case 5:
                    running = false;
                    System.out.println("Thank you for using East West University Library!");
                    break;
                default:
                    System.out.println("Invalid choice. Please select 1-5.");
            }
        }
        sc.close();
    }
}