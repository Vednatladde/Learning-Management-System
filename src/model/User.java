package model;

import java.io.Serializable;

/**
 * User class representing a user in the E-Learning Management System
 * Users can be Student, Instructor, or Admin
 */
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String userId;
    private String username;
    private String password;
    private String role; // ADMIN, INSTRUCTOR, STUDENT
    private String email;
    private String fullName;
    private String joinDate;

    // Constructor
    public User(String userId, String username, String password, String role, 
                String email, String fullName, String joinDate) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = role;
        this.email = email;
        this.fullName = fullName;
        this.joinDate = joinDate;
    }

    // Getters and Setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    @Override
    public String toString() {
        return userId + "|" + username + "|" + password + "|" + role + "|" + 
               email + "|" + fullName + "|" + joinDate;
    }

    // Parse user from string format
    public static User fromString(String data) {
        String[] parts = data.split("\\|");
        if (parts.length == 7) {
            return new User(parts[0], parts[1], parts[2], parts[3], 
                          parts[4], parts[5], parts[6]);
        }
        return null;
    }
}
