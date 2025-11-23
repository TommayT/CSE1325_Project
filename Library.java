import java.util.*;

public class Library {
    private List<Book> books;

    public Library() {
        books = FileUtils.loadBooks();
    }

    public List<Book> getBooks() {
        return books;
    }

    public void addBook(Book newBook) {
        books.add(newBook);
        FileUtils.saveBooks(books);
    }

    public boolean checkoutBook(String identifier, String studentName) {
        // Check if student already has a book checked out
        if (FileUtils.studentHasActiveCheckout(studentName)) {
            System.out.println("You already have a book checked out!");
            System.out.println("Please return it before checking out another book.");
            return false;
        }
        
        // Calculate due date (1 week from now)
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.add(java.util.Calendar.DAY_OF_YEAR, 7);
        String dueDate = new java.text.SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
        
        for (Book book : books) {
            // Check by title OR ID
            if (book.getTitle().equalsIgnoreCase(identifier.trim()) || 
                book.getId().equalsIgnoreCase(identifier.trim())) {
                
                if (!book.isAvailable()) {
                    System.out.println("That book is already checked out.");
                    return false;
                }
                
                book.setAvailable(false);
                FileUtils.saveBooks(books);
                
                String currentDate = new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());
                Transaction transaction = new Transaction(book, studentName, currentDate, dueDate, false);
                FileUtils.appendTransaction(transaction.toString());
                
                System.out.println("Book checked out successfully to " + studentName);
                System.out.println("Due date: " + dueDate + " (7 days from today)");
                return true;
            }
        }
        System.out.println("Book not found in library.");
        return false;
    }

    public boolean returnBook(String title, String studentName) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title.trim())) {
                if (book.isAvailable()) {
                    System.out.println("That book is not checked out.");
                    return false;
                }
                
                book.setAvailable(true);
                FileUtils.saveBooks(books);
                
                // Mark the transaction as returned
                FileUtils.markTransactionReturned(book.getId(), studentName);
                
                System.out.println("Book returned successfully.");
                return true;
            }
        }
        System.out.println("Book not found in library.");
        return false;
    }

    public void displayAvailableBooks() {
        System.out.println("\n=== Available Books ===");
        boolean found = false;
        for (Book book : books) {
            if (book.isAvailable()) {
                System.out.println(book.getTitle() + " by " + book.getAuthor() + 
                                   " (" + book.getYear() + "), BookID: " + book.getId());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No available books.");
        }
    }

    public void displayCurrentlyCheckedOut() {
        System.out.println("\n=== Currently Checked Out Books ===");
        boolean foundAny = false;
        
        for (Book book : books) {
            if (!book.isAvailable()) {
                // Find the most recent transaction for this book
                String studentName = FileUtils.getStudentWithBook(book.getId());
                String dueDate = FileUtils.getDueDateForBook(book.getId());
                
                System.out.println("Book: " + book.getTitle() + " by " + book.getAuthor());
                System.out.println("  Checked out by: " + studentName);
                System.out.println("  Due date: " + dueDate);
                System.out.println();
                foundAny = true;
            }
        }
        
        if (!foundAny) {
            System.out.println("No books currently checked out.");
        }
    }

    public boolean removeBook(String title) {
        Iterator<Book> iterator = books.iterator();
        while (iterator.hasNext()) {
            Book book = iterator.next();
            if (book.getTitle().equalsIgnoreCase(title.trim())) {
                iterator.remove();
                FileUtils.saveBooks(books);
                System.out.println("Book removed successfully.");
                return true;
            }
        }
        System.out.println("Book not found.");
        return false;
    }
}