// Represents a student user
public class Student extends User {
    private List<Book> borrowedBooks;

    public Student(String id, String name) {
        // Initialize student info
    }

    @Override
    public void displayMenu(Library library) {
        // Student-specific menu:
        // 1. View books
        // 2. Borrow
        // 3. Return
        // 4. Logout
    }
}
