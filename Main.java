// Entry point of the program
public class Main {
import Java.util.Scanner;
import Java.util.Scanner;
import Java.io.FileWriter;
import Java.io.FileReader;
import Java.io.BufferedReader;
import Java.io.BufferedWriter;
import Java.io.IOException;

public static void student(int choice,Scanner input) {
		System.out.println("Welcome Student");
		while(choice!=4) {
		System.out.println("What would you like to do:");
		System.out.println("1.Display available books");
		System.out.println("2.Checkout a book");
		System.out.println("3.Return a book");
		System.out.println("4.Exit");
		choice=input.nextInt();
		input.nextLine();
			}
	}
	
	public static void admin(int choice,Scanner input) {
		//should have a text file for admin login info to verify they are an admin, code needed once we have that
			System.out.println("Welcome Admin");//would like to have a way to pull their name from the file and use it in the welcome message
				while(choice!=3) {
					System.out.println("What would you like to do:");
					System.out.println("1.Add a book");
					System.out.println("2.Check due date of checked out book");
					System.out.println("3.Remove book from availability");
					System.out.println("4.Display available books");
					System.out.println("5.Display checked out books and who they are with");
					System.out.println("6.Exit");
					choice=input.nextInt();
					input.nextLine();
					}
	}
    public static void main(String[] args) {
        String user;
	int choice=0;
	Scanner input=new Scanner(System.in);
	System.out.println("Welcome to the Maverick Library Database");
	System.out.println("----------------------------------------");
	
	System.out.println("Are you a Student or an Administrator:");
	user=input.nextLine();
	//the if/else statements should be changed to methods once we are satisfied with how they are and what they do to condense the code
	if(user.equalsIgnoreCase("Student")) {
		student(choice,input);
		}
	else if(user.equalsIgnoreCase("Administrator")) {
		admin(choice,input);
		}
	else {
		System.out.println("Invalid input");
		System.out.println("Are you a Student or an Administrator:");
		user=input.nextLine();
		if(user.equalsIgnoreCase("Student")) {
			student(choice,input);
			}
		else if(user.equalsIgnoreCase("Administrator")) {
			admin(choice,input);
			}
	}
    }
}
