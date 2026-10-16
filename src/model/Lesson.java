package model;

import java.io.Serializable;

/**
 * Lesson class representing a lesson within a course
 */
public class Lesson implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String lessonId;
    private String courseId;
    private String lessonTitle;
    private String lessonContent;
    private String createdDate;

    // Constructor
    public Lesson(String lessonId, String courseId, String lessonTitle, 
                  String lessonContent, String createdDate) {
        this.lessonId = lessonId;
        this.courseId = courseId;
        this.lessonTitle = lessonTitle;
        this.lessonContent = lessonContent;
        this.createdDate = createdDate;
    }

    // Getters and Setters
    public String getLessonId() {
        return lessonId;
    }

    public void setLessonId(String lessonId) {
        this.lessonId = lessonId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getLessonTitle() {
        return lessonTitle;
    }

    public void setLessonTitle(String lessonTitle) {
        this.lessonTitle = lessonTitle;
    }

    public String getLessonContent() {
        return lessonContent;
    }

    public void setLessonContent(String lessonContent) {
        this.lessonContent = lessonContent;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return lessonId + "|" + courseId + "|" + lessonTitle + "|" + 
               lessonContent + "|" + createdDate;
    }

    // Parse lesson from string format
    public static Lesson fromString(String data) {
        String[] parts = data.split("\\|", 5);
        if (parts.length == 5) {
            return new Lesson(parts[0], parts[1], parts[2], parts[3], parts[4]);
        }
        return null;
    }
}
