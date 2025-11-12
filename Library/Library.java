public class Library {
    private List<Book> books = new ArrayList<>();
    private List<Transaction> transactions = new ArrayList<>();

    public Library() {
        books = FileUtils.loadBooks();  // Load books from txt file
    }

    public void addBook(Book newBook) {
        books.add(newBook);
        FileUtils.appendBook(newBook);  // Immediately save new book
    }

    public void checkoutBook(String bookId, Student student) {
        // logic ...
        // FileUtils.appendTransaction(transaction);
        // FileUtils.saveBooks(books); // update book availability
    }
}
