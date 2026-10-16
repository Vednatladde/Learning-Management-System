package model;

import java.io.Serializable;

/**
 * EnrolledCourse class representing a student's enrollment in a course
 */
public class EnrolledCourse implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String enrollmentId;
    private String studentId;
    private String courseId;
    private String courseName;
    private String enrollmentDate;
    private String status; // ACTIVE, COMPLETED, DROPPED

    // Constructor
    public EnrolledCourse(String enrollmentId, String studentId, String courseId, 
                         String courseName, String enrollmentDate, String status) {
        this.enrollmentId = enrollmentId;
        this.studentId = studentId;
        this.courseId = courseId;
        this.courseName = courseName;
        this.enrollmentDate = enrollmentDate;
        this.status = status;
    }

    // Getters and Setters
    public String getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(String enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(String enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return enrollmentId + "|" + studentId + "|" + courseId + "|" + 
               courseName + "|" + enrollmentDate + "|" + status;
    }

    // Parse enrolled course from string format
    public static EnrolledCourse fromString(String data) {
        String[] parts = data.split("\\|");
        if (parts.length == 6) {
            return new EnrolledCourse(parts[0], parts[1], parts[2], parts[3], 
                                     parts[4], parts[5]);
        }
        return null;
    }
}
