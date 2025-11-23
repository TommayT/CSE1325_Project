import java.util.Scanner;

public class Student extends User {
    
    public Student(String id, String name) {
        super(id, name);
    }

    @Override
    public void displayMenu(Library library, Scanner input) {
        int choice = 0;
        System.out.println("Welcome " + getName());
        
        // Show current checkout status
        String currentBook = FileUtils.getCurrentlyCheckedOutBook(getName());
        if (currentBook != null) {
            System.out.println("You currently have checked out: " + currentBook);
        }
        
        while(choice != 4) {
            System.out.println("\nWhat would you like to do:");
            System.out.println("1. Display available books");
            System.out.println("2. Checkout a book");
            System.out.println("3. Return a book");
            System.out.println("4. Exit");
            
            choice = input.nextInt();
            input.nextLine();
            
            switch(choice) {
                case 1:
                    library.displayAvailableBooks();
                    break;
                case 2:
                    System.out.println("Enter book to checkout (title or ID):");
                    String checkoutTitle = input.nextLine();
                    library.checkoutBook(checkoutTitle, getName());
                    break;
                case 3:
                    System.out.println("Enter book to return (title):");
                    String returnTitle = input.nextLine();
                    library.returnBook(returnTitle, getName());
                    break;
                case 4:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
