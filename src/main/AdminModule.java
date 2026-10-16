package main;

import model.User;
import model.Course;
import service.AuthenticationService;
import service.UserService;
import service.CourseService;
import util.InputHandler;
import java.util.List;

/**
 * AdminModule class for handling admin operations
 */
public class AdminModule {
    
    /**
     * Display admin menu
     */
    public static void displayAdminMenu() {
        while (AuthenticationService.isAdmin()) {
            InputHandler.displayHeader("ADMIN DASHBOARD");
            System.out.println("1. View All Users");
            System.out.println("2. Add New Instructor");
            System.out.println("3. Remove User");
            System.out.println("4. View All Courses");
            System.out.println("5. Delete Course");
            System.out.println("6. View System Statistics");
            System.out.println("7. Logout");
            System.out.println("==========================================");
            
            int choice = InputHandler.readInteger("Enter your choice: ");
            
            switch (choice) {
                case 1:
                    viewAllUsers();
                    break;
                case 2:
                    addNewInstructor();
                    break;
                case 3:
                    removeUser();
                    break;
                case 4:
                    viewAllCourses();
                    break;
                case 5:
                    deleteCourse();
                    break;
                case 6:
                    viewSystemStatistics();
                    break;
                case 7:
                    AuthenticationService.logout();
                    return;
                default:
                    InputHandler.displayError("Invalid choice!");
            }
            
            InputHandler.pressEnter();
        }
    }

    /**
     * View all users
     */
    private static void viewAllUsers() {
        InputHandler.displayHeader("ALL USERS");
        
        List<User> users = UserService.getAllUsers();
        
        if (users.isEmpty()) {
            InputHandler.displayMessage("No users found.");
            return;
        }
        
        System.out.println("Total Users: " + users.size());
        System.out.println("==========================================");
        
        for (User user : users) {
            System.out.println("ID: " + user.getUserId());
            System.out.println("Username: " + user.getUsername());
            System.out.println("Full Name: " + user.getFullName());
            System.out.println("Role: " + user.getRole());
            System.out.println("Email: " + user.getEmail());
            System.out.println("Join Date: " + user.getJoinDate());
            System.out.println("------------------------------------------");
        }
    }

    /**
     * Add new instructor
     */
    private static void addNewInstructor() {
        InputHandler.displayHeader("ADD NEW INSTRUCTOR");
        
        String username = InputHandler.readString("Enter username: ");
        
        if (UserService.usernameExists(username)) {
            InputHandler.displayError("Username already exists!");
            return;
        }
        
        String password = InputHandler.readString("Enter password: ");
        String email = InputHandler.readString("Enter email: ");
        String fullName = InputHandler.readString("Enter full name: ");
        
        AuthenticationService.register(username, password, "INSTRUCTOR", email, fullName);
    }

    /**
     * Remove user
     */
    private static void removeUser() {
        InputHandler.displayHeader("REMOVE USER");
        
        String userId = InputHandler.readString("Enter User ID to remove: ");
        User user = UserService.getUserById(userId);
        
        if (user == null) {
            InputHandler.displayError("User not found!");
            return;
        }
        
        System.out.println("User Details:");
        System.out.println("ID: " + user.getUserId());
        System.out.println("Username: " + user.getUsername());
        System.out.println("Full Name: " + user.getFullName());
        System.out.println("Role: " + user.getRole());
        
        String confirm = InputHandler.readString("Are you sure you want to delete this user? (yes/no): ");
        
        if (confirm.equalsIgnoreCase("yes")) {
            if (UserService.deleteUser(userId)) {
                InputHandler.displaySuccess("User deleted successfully!");
            } else {
                InputHandler.displayError("Failed to delete user!");
            }
        } else {
            InputHandler.displayMessage("Deletion cancelled.");
        }
    }

    /**
     * View all courses
     */
    private static void viewAllCourses() {
        InputHandler.displayHeader("ALL COURSES");
        
        List<Course> courses = CourseService.getAllCourses();
        
        if (courses.isEmpty()) {
            InputHandler.displayMessage("No courses found.");
            return;
        }
        
        System.out.println("Total Courses: " + courses.size());
        System.out.println("==========================================");
        
        for (Object course : courses) {
            model.Course c = (model.Course) course;
            System.out.println("Course ID: " + c.getCourseId());
            System.out.println("Course Name: " + c.getCourseName());
            System.out.println("Description: " + c.getDescription());
            System.out.println("Instructor: " + c.getInstructorName());
            System.out.println("Created Date: " + c.getCreatedDate());
            System.out.println("Students Enrolled: " + c.getEnrollmentCount());
            System.out.println("------------------------------------------");
        }
    }

    /**
     * Delete course
     */
    private static void deleteCourse() {
        InputHandler.displayHeader("DELETE COURSE");
        
        String courseId = InputHandler.readString("Enter Course ID to delete: ");
        
        Object courseObj = CourseService.getCourseById(courseId);
        if (courseObj == null) {
            InputHandler.displayError("Course not found!");
            return;
        }
        
        model.Course course = (model.Course) courseObj;
        System.out.println("Course Details:");
        System.out.println("ID: " + course.getCourseId());
        System.out.println("Name: " + course.getCourseName());
        System.out.println("Instructor: " + course.getInstructorName());
        
        String confirm = InputHandler.readString("Are you sure you want to delete this course? (yes/no): ");
        
        if (confirm.equalsIgnoreCase("yes")) {
            if (CourseService.deleteCourse(courseId)) {
                InputHandler.displaySuccess("Course deleted successfully!");
            } else {
                InputHandler.displayError("Failed to delete course!");
            }
        } else {
            InputHandler.displayMessage("Deletion cancelled.");
        }
    }

    /**
     * View system statistics
     */
    private static void viewSystemStatistics() {
        InputHandler.displayHeader("SYSTEM STATISTICS");
        
        List<User> allUsers = UserService.getAllUsers();
        List<User> students = UserService.getUsersByRole("STUDENT");
        List<User> instructors = UserService.getUsersByRole("INSTRUCTOR");
        List<Course> courses = CourseService.getAllCourses();
        
        System.out.println("Total Users: " + allUsers.size());
        System.out.println("Total Students: " + students.size());
        System.out.println("Total Instructors: " + instructors.size());
        System.out.println("Total Courses: " + courses.size());
        System.out.println("==========================================");
    }
}
