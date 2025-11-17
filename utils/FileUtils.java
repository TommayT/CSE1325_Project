import java.util.*;
import java.io.*;

public class FileUtils {

    private static final String BOOKS_FILE = "books.txt";
    private static final String TRANSACTIONS_FILE = "transactions.txt";

    // Load all books from books.txt
    public static List<Book> loadBooks() {
        List<Book> books = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(BOOKS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    books.add(Book.fromString(line));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading books.txt: " + e.getMessage());
        }

        return books;
    }

    // Save all books back into the file (overwrite)
    public static void saveBooks(List<Book> books) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(BOOKS_FILE))) {
            for (Book b : books) {
                bw.write(b.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to books.txt: " + e.getMessage());
        }
    }

    // Append a single new book entry to books.txt
    public static void appendBook(Book book) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(BOOKS_FILE, true))) {
            bw.write(book.toString());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error appending book: " + e.getMessage());
        }
    }

    // Append a transaction record to transactions.txt
    public static void appendTransaction(String transactionDetails) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(TRANSACTIONS_FILE, true))) {
            bw.write(transactionDetails);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error writing transaction: " + e.getMessage());
        }
    }
}
