package service;

import model.User;
import util.FileHandler;
import java.util.ArrayList;
import java.util.List;

/**
 * UserService class for managing user operations
 */
public class UserService {
    
    /**
     * Get all users from file
     */
    public static List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        List<String> lines = FileHandler.readFile(FileHandler.getUsersFile());
        
        for (String line : lines) {
            User user = User.fromString(line);
            if (user != null) {
                users.add(user);
            }
        }
        return users;
    }

    /**
     * Get user by username
     */
    public static User getUserByUsername(String username) {
        List<User> users = getAllUsers();
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Get user by ID
     */
    public static User getUserById(String userId) {
        List<User> users = getAllUsers();
        for (User user : users) {
            if (user.getUserId().equals(userId)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Add new user
     */
    public static boolean addUser(User user) {
        // Check if username already exists
        if (getUserByUsername(user.getUsername()) != null) {
            return false;
        }
        
        FileHandler.appendToFile(FileHandler.getUsersFile(), user.toString());
        return true;
    }

    /**
     * Update user
     */
    public static boolean updateUser(User updatedUser) {
        List<User> users = getAllUsers();
        List<String> lines = new ArrayList<>();
        
        boolean found = false;
        for (User user : users) {
            if (user.getUserId().equals(updatedUser.getUserId())) {
                lines.add(updatedUser.toString());
                found = true;
            } else {
                lines.add(user.toString());
            }
        }
        
        if (found) {
            FileHandler.writeFile(FileHandler.getUsersFile(), lines);
        }
        return found;
    }

    /**
     * Delete user by ID
     */
    public static boolean deleteUser(String userId) {
        List<User> users = getAllUsers();
        List<String> lines = new ArrayList<>();
        
        boolean found = false;
        for (User user : users) {
            if (!user.getUserId().equals(userId)) {
                lines.add(user.toString());
            } else {
                found = true;
            }
        }
        
        if (found) {
            FileHandler.writeFile(FileHandler.getUsersFile(), lines);
        }
        return found;
    }

    /**
     * Generate unique user ID
     */
    public static String generateUserId() {
        List<User> users = getAllUsers();
        int maxId = 0;
        
        for (User user : users) {
            try {
                int id = Integer.parseInt(user.getUserId().substring(1));
                if (id > maxId) {
                    maxId = id;
                }
            } catch (NumberFormatException e) {
                // Ignore
            }
        }
        
        return "U" + (maxId + 1);
    }

    /**
     * Get all users by role
     */
    public static List<User> getUsersByRole(String role) {
        List<User> users = getAllUsers();
        List<User> filteredUsers = new ArrayList<>();
        
        for (User user : users) {
            if (user.getRole().equals(role)) {
                filteredUsers.add(user);
            }
        }
        
        return filteredUsers;
    }

    /**
     * Check if username exists
     */
    public static boolean usernameExists(String username) {
        return getUserByUsername(username) != null;
    }
}
