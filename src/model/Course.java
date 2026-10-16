package model;

import java.io.Serializable;

/**
 * Course class representing a course in the E-Learning Management System
 */
public class Course implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String courseId;
    private String courseName;
    private String description;
    private String instructorId;
    private String instructorName;
    private String createdDate;
    private int enrollmentCount;

    // Constructor
    public Course(String courseId, String courseName, String description, 
                  String instructorId, String instructorName, String createdDate) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.description = description;
        this.instructorId = instructorId;
        this.instructorName = instructorName;
        this.createdDate = createdDate;
        this.enrollmentCount = 0;
    }

    // Getters and Setters
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(String instructorId) {
        this.instructorId = instructorId;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public int getEnrollmentCount() {
        return enrollmentCount;
    }

    public void setEnrollmentCount(int enrollmentCount) {
        this.enrollmentCount = enrollmentCount;
    }

    @Override
    public String toString() {
        return courseId + "|" + courseName + "|" + description + "|" + 
               instructorId + "|" + instructorName + "|" + createdDate + "|" + enrollmentCount;
    }

    // Parse course from string format
    public static Course fromString(String data) {
        String[] parts = data.split("\\|");
        if (parts.length >= 7) {
            Course course = new Course(parts[0], parts[1], parts[2], parts[3], 
                                      parts[4], parts[5]);
            try {
                course.setEnrollmentCount(Integer.parseInt(parts[6]));
            } catch (NumberFormatException e) {
                course.setEnrollmentCount(0);
            }
            return course;
        }
        return null;
    }
}
