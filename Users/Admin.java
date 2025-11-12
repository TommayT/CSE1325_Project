// Represents an admin/staff user
public class Admin extends User {
    public Admin(String id, String name) {
        // Initialize admin info
    }

    @Override
    public void displayMenu(Library library) {
        // Admin-specific menu:
        // 1. View all transactions
        // 2. Add books
        // 3. Logout
    }
}
