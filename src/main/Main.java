package main;

import service.AuthenticationService;
import util.InputHandler;

/**
 * Main class - Entry point for the E-Learning Management System
 * Menu-driven console application
 */
public class Main {
    
    public static void main(String[] args) {
        displayWelcome();
        mainMenu();
        InputHandler.closeScanner();
    }

    /**
     * Display welcome message
     */
    private static void displayWelcome() {
        InputHandler.displaySeparator();
        System.out.println("\t\tWELCOME TO E-LEARNING MANAGEMENT SYSTEM");
        System.out.println("\t\t\tVersion 1.0");
        InputHandler.displaySeparator();
    }

    /**
     * Main menu - Before login
     */
    private static void mainMenu() {
        while (true) {
            InputHandler.displayHeader("MAIN MENU");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.println("==========================================");
            
            int choice = InputHandler.readInteger("Enter your choice: ");
            
            switch (choice) {
                case 1:
                    registerMenu();
                    break;
                case 2:
                    loginMenu();
                    break;
                case 3:
                    exitApplication();
                    return;
                default:
                    InputHandler.displayError("Invalid choice!");
            }
            
            InputHandler.pressEnter();
        }
    }

    /**
     * Register menu
     */
    private static void registerMenu() {
        InputHandler.displayHeader("USER REGISTRATION");
        
        System.out.println("Select user role:");
        System.out.println("1. Student");
        System.out.println("2. Instructor");
        System.out.println("3. Admin");
        System.out.println("==========================================");
        
        int roleChoice = InputHandler.readInteger("Enter role choice: ");
        
        String role;
        switch (roleChoice) {
            case 1:
                role = "STUDENT";
                break;
            case 2:
                role = "INSTRUCTOR";
                break;
            case 3:
                role = "ADMIN";
                break;
            default:
                InputHandler.displayError("Invalid role selection!");
                return;
        }
        
        String username = InputHandler.readString("Enter username: ");
        String password = InputHandler.readString("Enter password: ");
        String email = InputHandler.readString("Enter email: ");
        String fullName = InputHandler.readString("Enter full name: ");
        
        AuthenticationService.register(username, password, role, email, fullName);
    }

    /**
     * Login menu
     */
    private static void loginMenu() {
        InputHandler.displayHeader("USER LOGIN");
        
        String username = InputHandler.readString("Enter username: ");
        String password = InputHandler.readString("Enter password: ");
        
        if (AuthenticationService.login(username, password)) {
            // User logged in successfully
            dashboardMenu();
        }
    }

    /**
     * Dashboard menu - After login
     */
    private static void dashboardMenu() {
        if (AuthenticationService.isAdmin()) {
            AdminModule.displayAdminMenu();
        } else if (AuthenticationService.isInstructor()) {
            InstructorModule.displayInstructorMenu();
        } else if (AuthenticationService.isStudent()) {
            StudentModule.displayStudentMenu();
        }
    }

    /**
     * Exit application
     */
    private static void exitApplication() {
        InputHandler.displaySeparator();
        System.out.println("\t\tThank you for using E-Learning Management System!");
        System.out.println("\t\t\tGoodbye!");
        InputHandler.displaySeparator();
    }
}
