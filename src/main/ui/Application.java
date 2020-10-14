package ui;

import java.util.Scanner;

// Represents the log in interface of the restaurant application

public class Application {
    private Scanner in;
    private String user;

    // EFFECTS: runs the log in interface of the restaurant application
    public Application() {
        loadApplication();
    }

    // EFFECTS: Print instructions for the user on the log in interface and takes user input
    public void loadApplication() {
        in = new Scanner(System.in);
        System.out.println("Please choose a role to sign in: \n"
                +      "Enter 1 for Manager \n"
                +      "Enter 2 for Customer");
        user = in.nextLine();

        if (user.equals("1")) {
            new OwnerInterface();
        } else if (user.equals("2")) {
            new CustomerInterface();
        }
    }
}
