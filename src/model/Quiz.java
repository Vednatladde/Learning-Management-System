package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Quiz class representing a quiz in a course
 */
public class Quiz implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String quizId;
    private String courseId;
    private String quizTitle;
    private String quizDescription;
    private String instructorId;
    private String createdDate;
    private int totalQuestions;
    private List<QuizQuestion> questions;

    // Constructor
    public Quiz(String quizId, String courseId, String quizTitle, 
                String quizDescription, String instructorId, String createdDate) {
        this.quizId = quizId;
        this.courseId = courseId;
        this.quizTitle = quizTitle;
        this.quizDescription = quizDescription;
        this.instructorId = instructorId;
        this.createdDate = createdDate;
        this.totalQuestions = 0;
        this.questions = new ArrayList<>();
    }

    // Getters and Setters
    public String getQuizId() {
        return quizId;
    }

    public void setQuizId(String quizId) {
        this.quizId = quizId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getQuizTitle() {
        return quizTitle;
    }

    public void setQuizTitle(String quizTitle) {
        this.quizTitle = quizTitle;
    }

    public String getQuizDescription() {
        return quizDescription;
    }

    public void setQuizDescription(String quizDescription) {
        this.quizDescription = quizDescription;
    }

    public String getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(String instructorId) {
        this.instructorId = instructorId;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public List<QuizQuestion> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuizQuestion> questions) {
        this.questions = questions;
    }

    public void addQuestion(QuizQuestion question) {
        this.questions.add(question);
        this.totalQuestions++;
    }

    @Override
    public String toString() {
        return quizId + "|" + courseId + "|" + quizTitle + "|" + 
               quizDescription + "|" + instructorId + "|" + createdDate + "|" + totalQuestions;
    }

    // Parse quiz from string format
    public static Quiz fromString(String data) {
        String[] parts = data.split("\\|");
        if (parts.length == 7) {
            Quiz quiz = new Quiz(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5]);
            try {
                quiz.setTotalQuestions(Integer.parseInt(parts[6]));
            } catch (NumberFormatException e) {
                quiz.setTotalQuestions(0);
            }
            return quiz;
        }
        return null;
    }
}
