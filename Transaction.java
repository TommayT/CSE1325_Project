import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {
    private Book book;
    private String studentName;
    private String checkoutDate;
    private String dueDate;
    private boolean returned;

    public Transaction(Book book, String studentName, String checkoutDate, String dueDate, boolean returned) {
        this.book = book;
        this.studentName = studentName;
        this.checkoutDate = checkoutDate;
        this.dueDate = dueDate;
        this.returned = returned;
    }

    public Book getBook() { return book; }
    public String getStudentName() { return studentName; }
    public String getCheckoutDate() { return checkoutDate; }
    public String getDueDate() { return dueDate; }
    public boolean isReturned() { return returned; }
    public void setReturned(boolean returned) { this.returned = returned; }

    public String toString() {
        return book.getId() + "," + book.getTitle() + "," + book.getAuthor() + "," + 
               book.getYear() + "," + studentName + "," + checkoutDate + "," + dueDate + "," + returned;
    }

    public static Transaction fromString(String line) {
        String[] parts = line.split(",");
        if (parts.length < 8) return null;
        
        // Recreate book object
        Book book = new Book(parts[0].trim(), parts[1].trim(), parts[2].trim(), parts[3].trim(), false);
        String studentName = parts[4].trim();
        String checkoutDate = parts[5].trim();
        String dueDate = parts[6].trim();
        boolean returned = Boolean.parseBoolean(parts[7].trim());
        
        return new Transaction(book, studentName, checkoutDate, dueDate, returned);
    }
}