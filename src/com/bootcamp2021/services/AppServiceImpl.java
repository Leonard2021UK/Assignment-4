package com.bootcamp2021.services;

import com.bootcamp2021.interfaces.AppService;
import com.bootcamp2021.interfaces.User;

import java.util.List;
import java.util.Scanner;

public class AppServiceImpl implements AppService {

    private final Scanner scanner;
    FileServiceImpl fileService;
    UserServiceImpl userService;

    public AppServiceImpl() {

        // instantiate services
        this.fileService = new FileServiceImpl();
        this.userService = new UserServiceImpl();

        // initiate scanner
        this.scanner = new Scanner(System.in);

    }

    /**
     *Starts the application
     */
    public void start() {

        // read the data from the file into an array
        this.fileService.readFile();

        // create users based on the data read from the file
        this.userService.setUsers(this.fileService.getDataBuffer());

        // reset dataBuffer in file service
        this.fileService.resetDataBuffer();

        // It uses try-with-resources where the resource "Scanner" is automatically closed once finished (normally or abruptly),
        // hence no need for finally block. Any object is consider to be a resource if implements AutoCloseable interface.
        try (this.scanner) {

            while ( (this.userService.getLoggedInUser() == null) && this.userService.hasMoreLoginAttempts() ) {

                loginUser(this.scanner);
            }


        } catch (Exception e) {
            System.out.println("Ooops, something went wrong!");
            System.out.println(e.getMessage());
        }

    }


    /**
     * Shows the GUI for the logged in user and prompt user to choose an option
     */
    public void showLoggedInUserUI(){
        String loggedInUsername = this.userService.getLoggedInUser().getName();
        System.out.println("Welcome " + loggedInUsername);
        System.out.println("---------------------------");
        this.printOptionMenu();

        // read user input
        String option = this.scanner.nextLine();

        this.evaluateOption(option);
    }

    /**
     * Starts the login process
     * @param scanner - text scanner
     */
    public void loginUser(Scanner scanner) {

        System.out.println("Please enter your username:");
        String username = scanner.nextLine();

        System.out.println("Please enter your password:");
        String password = scanner.nextLine();

        this.userService.authenticateUser(username, password);

        if (this.userService.getLoggedInUser() != null) {

            this.showLoggedInUserUI();

        } else {
            // prevents giving the chance to re try at the final attempt
            if (this.userService.hasMoreLoginAttempts()) {
                System.out.println("Invalid login, please try again.");
            } else {
                System.out.println("Too many failed login attempts, you are now locked out.");

            }
        }
    }

    /**
     * Prints the option menu
     */
    public void printOptionMenu(){

        System.out.println("Please choose from the following options:");

        User loggedInUser = this.userService.getLoggedInUser();
        List<String> authorities = loggedInUser.getAuthorities();
        for (String authority:authorities){
            System.out.println(authority);
        }

    }


    /**
     * Evaluates the chosen option
     * @param option - A String representation of the user's input
     */
    public void evaluateOption(String option){

        User loggedInUser = this.userService.getLoggedInUser();

        switch(option){
            // Log in as another user, this option is valid only for super users
            case("0"):
                if(loggedInUser.isSuperUser()){
                    System.out.println("Which user would you like to login as? (Type in a valid username)");
                    String newUserToSwitch = this.scanner.nextLine();
                    boolean isSwitchSuccessful = this.userService.switchUser(newUserToSwitch);
                    if(isSwitchSuccessful){
                        showLoggedInUserUI();
                    }
                }else {
                    System.out.println("Not a valid option!");
                }
                break;
            // Update username
            case("1"):
                System.out.println("Please type in your new username:");
                String newUsername = this.scanner.nextLine();
                loggedInUser.setUsername(newUsername);
                // update users record
                this.userService.upDateUsersRecord();
                // write into the file
                this.fileService.writeFile(this.userService.getUsers());
                showLoggedInUserUI();
                break;
            // Update password
            case("2"):
                System.out.println("Please type in your new password:");
                String newPassword = this.scanner.nextLine();
                loggedInUser.setPassword(newPassword);
                // update users record
                this.userService.upDateUsersRecord();
                // write into the file
                this.fileService.writeFile(this.userService.getUsers());
                showLoggedInUserUI();
                break;
            // Update name
            case("3"):
                System.out.println("Please type in your new name:");
                String newName = this.scanner.nextLine();
                loggedInUser.setName(newName);
                // update users record
                this.userService.upDateUsersRecord();
                // write into the file
                this.fileService.writeFile(this.userService.getUsers());
                showLoggedInUserUI();
                break;
            // Exit
            case("4"):
                System.out.println("Exit");
                break;
            default:
                System.out.println("Not a valid option!");
        }

    }
}
