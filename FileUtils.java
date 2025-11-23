import java.util.*;
import java.io.*;

public class FileUtils {
    private static final String BOOKS_FILE = "books.txt";
    private static final String TRANSACTIONS_FILE = "transactions.txt";
    private static final String STUDENTS_FILE = "students.txt";
    private static final String ADMINS_FILE = "admins.txt";

    public static boolean verifyStudent(String id, String password) {
        try (BufferedReader br = new BufferedReader(new FileReader(STUDENTS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 3) {
                    if (parts[0].trim().equals(id) && parts[2].trim().equals(password)) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading student credentials: " + e.getMessage());
        }
        return false;
    }

    public static String getStudentName(String id) {
        try (BufferedReader br = new BufferedReader(new FileReader(STUDENTS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2 && parts[0].trim().equals(id)) {
                    return parts[1].trim();
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading student name: " + e.getMessage());
        }
        return "Student";
    }

    public static boolean registerStudent(String id, String name, String password) {
        // Check if ID already exists
        try (BufferedReader br = new BufferedReader(new FileReader(STUDENTS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 1 && parts[0].trim().equals(id)) {
                    return false; // ID already exists
                }
            }
        } catch (IOException e) {
            // File might not exist yet, which is fine for first signup
        }
        
        // Write new student
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(STUDENTS_FILE, true))) {
            bw.write(id + "," + name + "," + password);
            bw.newLine();
            return true;
        } catch (IOException e) {
            System.out.println("Error creating student account: " + e.getMessage());
            return false;
        }
    }

    // Admin authentication methods
    public static boolean verifyAdmin(String id, String password) {
        try (BufferedReader br = new BufferedReader(new FileReader(ADMINS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 3) {
                    if (parts[0].trim().equals(id) && parts[2].trim().equals(password)) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading admin credentials: " + e.getMessage());
        }
        return false;
    }

    public static String getAdminName(String id) {
        try (BufferedReader br = new BufferedReader(new FileReader(ADMINS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2 && parts[0].trim().equals(id)) {
                    return parts[1].trim();
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading admin name: " + e.getMessage());
        }
        return "Admin";
    }

    public static boolean registerAdmin(String id, String name, String password) {
        // Check if ID already exists
        try (BufferedReader br = new BufferedReader(new FileReader(ADMINS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 1 && parts[0].trim().equals(id)) {
                    return false; // ID already exists
                }
            }
        } catch (IOException e) {
            // File might not exist yet, which is fine for first signup
        }
        
        // Write new admin
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ADMINS_FILE, true))) {
            bw.write(id + "," + name + "," + password);
            bw.newLine();
            return true;
        } catch (IOException e) {
            System.out.println("Error creating admin account: " + e.getMessage());
            return false;
        }
    }

    public static List<Book> loadBooks() {
        List<Book> books = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(BOOKS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    Book book = Book.fromString(line);
                    if (book != null) {
                        books.add(book);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("books.txt not found. Creating new file.");
        } catch (IOException e) {
            System.out.println("Error reading books.txt: " + e.getMessage());
        }
        return books;
    }

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

    public static void appendTransaction(String transactionDetails) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(TRANSACTIONS_FILE, true))) {
            bw.write(transactionDetails);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error writing transaction: " + e.getMessage());
        }
    }

    public static void displayTransactions() {
        System.out.println("\n=== All Transactions ===");
        try (BufferedReader br = new BufferedReader(new FileReader(TRANSACTIONS_FILE))) {
            String line;
            boolean found = false;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    String[] parts = line.split(",");
                    if (parts.length >= 6) {
                        System.out.println("Book: " + parts[1] + " | Student: " + 
                                           parts[4] + " | Due: " + parts[5]);
                        found = true;
                    }
                }
            }
            if (!found) {
                System.out.println("No transactions found.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("No transactions found.");
        } catch (IOException e) {
            System.out.println("Error reading transactions: " + e.getMessage());
        }
    }

    public static void displayAllTransactions() {
        System.out.println("\n=== All Transaction History ===");
        try (BufferedReader br = new BufferedReader(new FileReader(TRANSACTIONS_FILE))) {
            String line;
            boolean found = false;
            int count = 1;
            
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    String[] parts = line.split(",");
                    if (parts.length >= 6) {
                        System.out.println(count + ". Book: " + parts[1] + " by " + parts[2]);
                        System.out.println("   Student: " + parts[4]);
                        System.out.println("   Due Date: " + parts[5]);
                        System.out.println();
                        found = true;
                        count++;
                    }
                }
            }
            
            if (!found) {
                System.out.println("No transactions found.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("No transactions found.");
        } catch (IOException e) {
            System.out.println("Error reading transactions: " + e.getMessage());
        }
    }

    // Get the student who currently has a specific book
    public static String getStudentWithBook(String bookId) {
        String studentName = "Unknown";
        
        try (BufferedReader br = new BufferedReader(new FileReader(TRANSACTIONS_FILE))) {
            String line;

            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    String[] parts = line.split(",");
                    if (parts.length >= 5 && parts[0].trim().equals(bookId)) {
                        studentName = parts[4].trim();
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading transactions: " + e.getMessage());
        }
        
        return studentName;
    }

    public static String getDueDateForBook(String bookId) {
        String dueDate = "Unknown";
        
        try (BufferedReader br = new BufferedReader(new FileReader(TRANSACTIONS_FILE))) {
            String line;

            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    String[] parts = line.split(",");
                    if (parts.length >= 6 && parts[0].trim().equals(bookId)) {
                        dueDate = parts[5].trim();
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading transactions: " + e.getMessage());
        }
        
        return dueDate;
    }

        // Check if student has an active (unreturned) checkout
    public static boolean studentHasActiveCheckout(String studentName) {
        try (BufferedReader br = new BufferedReader(new FileReader(TRANSACTIONS_FILE))) {
            String line;
            
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    String[] parts = line.split(",");
                    if (parts.length >= 8) {
                        String transStudent = parts[4].trim();
                        boolean returned = Boolean.parseBoolean(parts[7].trim());
                        
                        // If this student has an unreturned book
                        if (transStudent.equals(studentName) && !returned) {
                            return true;
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error checking student checkout status: " + e.getMessage());
        }
        return false;
    }

    // Get the book title that student currently has checked out
    public static String getCurrentlyCheckedOutBook(String studentName) {
        try (BufferedReader br = new BufferedReader(new FileReader(TRANSACTIONS_FILE))) {
            String line;
            
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    String[] parts = line.split(",");
                    if (parts.length >= 8) {
                        String transStudent = parts[4].trim();
                        boolean returned = Boolean.parseBoolean(parts[7].trim());
                        
                        if (transStudent.equals(studentName) && !returned) {
                            return parts[1].trim(); // book title
                        }
                    }
                }
            }
        } catch (IOException e) {
            // File doesn't exist yet or error reading
        }
        return null;
    }

    // Mark a transaction as returned
    public static void markTransactionReturned(String bookId, String studentName) {
        List<String> lines = new ArrayList<>();
        boolean found = false;
        
        try (BufferedReader br = new BufferedReader(new FileReader(TRANSACTIONS_FILE))) {
            String line;
            
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    String[] parts = line.split(",");
                    
                    // Find the most recent unreturned transaction for this book and student
                    if (parts.length >= 8 && 
                        parts[0].trim().equals(bookId) && 
                        parts[4].trim().equals(studentName) &&
                        parts[7].trim().equals("false")) {
                        
                        // Mark as returned
                        parts[7] = "true";
                        line = String.join(",", parts);
                        found = true;
                    }
                }
                lines.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading transactions: " + e.getMessage());
            return;
        }
        
        // Write back all lines
        if (found) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(TRANSACTIONS_FILE))) {
                for (String line : lines) {
                    bw.write(line);
                    bw.newLine();
                }
            } catch (IOException e) {
                System.out.println("Error updating transaction: " + e.getMessage());
            }
        }
    }
}