package service;

import model.EnrolledCourse;
import util.FileHandler;
import java.util.ArrayList;
import java.util.List;

/**
 * EnrollmentService class for managing student enrollments
 */
public class EnrollmentService {
    
    /**
     * Get all enrollments from file
     */
    public static List<EnrolledCourse> getAllEnrollments() {
        List<EnrolledCourse> enrollments = new ArrayList<>();
        List<String> lines = FileHandler.readFile(FileHandler.getEnrollmentsFile());
        
        for (String line : lines) {
            EnrolledCourse enrollment = EnrolledCourse.fromString(line);
            if (enrollment != null) {
                enrollments.add(enrollment);
            }
        }
        return enrollments;
    }

    /**
     * Get enrollments by student ID
     */
    public static List<EnrolledCourse> getEnrollmentsByStudent(String studentId) {
        List<EnrolledCourse> enrollments = getAllEnrollments();
        List<EnrolledCourse> studentEnrollments = new ArrayList<>();
        
        for (EnrolledCourse enrollment : enrollments) {
            if (enrollment.getStudentId().equals(studentId)) {
                studentEnrollments.add(enrollment);
            }
        }
        return studentEnrollments;
    }

    /**
     * Get enrollments by course ID
     */
    public static List<EnrolledCourse> getEnrollmentsByCourse(String courseId) {
        List<EnrolledCourse> enrollments = getAllEnrollments();
        List<EnrolledCourse> courseEnrollments = new ArrayList<>();
        
        for (EnrolledCourse enrollment : enrollments) {
            if (enrollment.getCourseId().equals(courseId)) {
                courseEnrollments.add(enrollment);
            }
        }
        return courseEnrollments;
    }

    /**
     * Check if student is already enrolled in a course
     */
    public static boolean isEnrolled(String studentId, String courseId) {
        List<EnrolledCourse> enrollments = getAllEnrollments();
        
        for (EnrolledCourse enrollment : enrollments) {
            if (enrollment.getStudentId().equals(studentId) && 
                enrollment.getCourseId().equals(courseId)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Add new enrollment
     */
    public static boolean addEnrollment(EnrolledCourse enrollment) {
        // Check if already enrolled
        if (isEnrolled(enrollment.getStudentId(), enrollment.getCourseId())) {
            return false;
        }
        
        FileHandler.appendToFile(FileHandler.getEnrollmentsFile(), enrollment.toString());
        
        // Update course enrollment count
        updateCourseEnrollmentCount(enrollment.getCourseId());
        
        return true;
    }

    /**
     * Generate unique enrollment ID
     */
    public static String generateEnrollmentId() {
        List<EnrolledCourse> enrollments = getAllEnrollments();
        int maxId = 0;
        
        for (EnrolledCourse enrollment : enrollments) {
            try {
                int id = Integer.parseInt(enrollment.getEnrollmentId().substring(1));
                if (id > maxId) {
                    maxId = id;
                }
            } catch (NumberFormatException e) {
                // Ignore
            }
        }
        
        return "E" + (maxId + 1);
    }

    /**
     * Update course enrollment count
     */
    private static void updateCourseEnrollmentCount(String courseId) {
        List<EnrolledCourse> enrollments = getEnrollmentsByCourse(courseId);
        int count = 0;
        
        for (EnrolledCourse enrollment : enrollments) {
            if (enrollment.getStatus().equals("ACTIVE")) {
                count++;
            }
        }
        
        CourseService.getAllCourses(); // This loads all courses
        // The enrollment count is tracked in the Course model
    }

    /**
     * Get total enrolled students in a course
     */
    public static int getTotalEnrolledStudents(String courseId) {
        List<EnrolledCourse> enrollments = getEnrollmentsByCourse(courseId);
        int count = 0;
        
        for (EnrolledCourse enrollment : enrollments) {
            if (enrollment.getStatus().equals("ACTIVE")) {
                count++;
            }
        }
        return count;
    }

    /**
     * Get enrollment by ID
     */
    public static EnrolledCourse getEnrollmentById(String enrollmentId) {
        List<EnrolledCourse> enrollments = getAllEnrollments();
        
        for (EnrolledCourse enrollment : enrollments) {
            if (enrollment.getEnrollmentId().equals(enrollmentId)) {
                return enrollment;
            }
        }
        return null;
    }
}
