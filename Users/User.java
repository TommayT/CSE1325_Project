// Abstract base class for all users
public abstract class User {
    protected String id;
    protected String name;

    public User(String id, String name) {
        // Common user initialization
    }

    public String getId() { /* return id */ }
    public String getName() { /* return name */ }

    public abstract void displayMenu(Library library);
}
