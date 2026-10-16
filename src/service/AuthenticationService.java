package service;

import model.User;
import util.FileHandler;
import util.InputHandler;

/**
 * AuthenticationService class for handling user authentication
 */
public class AuthenticationService {
    private static User currentUser = null;

    /**
     * Register a new user
     */
    public static boolean register(String username, String password, String role, 
                                   String email, String fullName) {
        // Check if username already exists
        if (UserService.usernameExists(username)) {
            InputHandler.displayError("Username already exists!");
            return false;
        }

        // Generate unique user ID
        String userId = UserService.generateUserId();
        String joinDate = java.time.LocalDate.now().toString();

        // Create new user
        User newUser = new User(userId, username, password, role, email, fullName, joinDate);

        // Add user to system
        if (UserService.addUser(newUser)) {
            InputHandler.displaySuccess("User registered successfully! Your User ID: " + userId);
            return true;
        } else {
            InputHandler.displayError("Failed to register user!");
            return false;
        }
    }

    /**
     * Login user
     */
    public static boolean login(String username, String password) {
        User user = UserService.getUserByUsername(username);

        if (user == null) {
            InputHandler.displayError("Username not found!");
            return false;
        }

        if (!user.getPassword().equals(password)) {
            InputHandler.displayError("Invalid password!");
            return false;
        }

        currentUser = user;
        InputHandler.displaySuccess("Login successful! Welcome " + user.getFullName());
        return true;
    }

    /**
     * Logout current user
     */
    public static void logout() {
        if (currentUser != null) {
            InputHandler.displaySuccess("Logged out successfully!");
            currentUser = null;
        }
    }

    /**
     * Get current logged-in user
     */
    public static User getCurrentUser() {
        return currentUser;
    }

    /**
     * Check if user is logged in
     */
    public static boolean isLoggedIn() {
        return currentUser != null;
    }

    /**
     * Get current user role
     */
    public static String getCurrentUserRole() {
        if (currentUser != null) {
            return currentUser.getRole();
        }
        return null;
    }

    /**
     * Check if current user is admin
     */
    public static boolean isAdmin() {
        return isLoggedIn() && currentUser.getRole().equals("ADMIN");
    }

    /**
     * Check if current user is instructor
     */
    public static boolean isInstructor() {
        return isLoggedIn() && currentUser.getRole().equals("INSTRUCTOR");
    }

    /**
     * Check if current user is student
     */
    public static boolean isStudent() {
        return isLoggedIn() && currentUser.getRole().equals("STUDENT");
    }
}
