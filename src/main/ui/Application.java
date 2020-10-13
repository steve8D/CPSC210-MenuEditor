package ui;

import java.util.Scanner;

public class Application {
    private Scanner in;
    private String user;

    public Application() {
        in = new Scanner(System.in);
    }

    // EFFECTS: Print instructions for the user and takes user input
    public void loadApplication() {
        System.out.println("Please choose a role to sign in: \n"
                +      "Enter 1 for Manager \n"
                +      "Enter 2 for Customer");
        user = in.nextLine();

        if (user.equals("1")) {
            OwnerInterface ownerInterface = new OwnerInterface();
            ownerInterface.loadOwnerInterface();
        } else if (user.equals("2")) {
            CustomerInterface customerInterface = new CustomerInterface();
            customerInterface.loadCustomerInterface();
        }
    }
}
