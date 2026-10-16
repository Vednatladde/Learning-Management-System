package model;

import java.io.Serializable;

/**
 * QuizAttempt class representing a student's quiz attempt
 */
public class QuizAttempt implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String attemptId;
    private String studentId;
    private String quizId;
    private String courseId;
    private int score;
    private int totalQuestions;
    private double percentage;
    private String attemptDate;
    private String status; // PASSED, FAILED, COMPLETED

    // Constructor
    public QuizAttempt(String attemptId, String studentId, String quizId, 
                       String courseId, int score, int totalQuestions, 
                       double percentage, String attemptDate, String status) {
        this.attemptId = attemptId;
        this.studentId = studentId;
        this.quizId = quizId;
        this.courseId = courseId;
        this.score = score;
        this.totalQuestions = totalQuestions;
        this.percentage = percentage;
        this.attemptDate = attemptDate;
        this.status = status;
    }

    // Getters and Setters
    public String getAttemptId() {
        return attemptId;
    }

    public void setAttemptId(String attemptId) {
        this.attemptId = attemptId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public String getAttemptDate() {
        return attemptDate;
    }

    public void setAttemptDate(String attemptDate) {
        this.attemptDate = attemptDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return attemptId + "|" + studentId + "|" + quizId + "|" + courseId + "|" + 
               score + "|" + totalQuestions + "|" + percentage + "|" + attemptDate + "|" + status;
    }

    // Parse quiz attempt from string format
    public static QuizAttempt fromString(String data) {
        String[] parts = data.split("\\|");
        if (parts.length == 9) {
            try {
                return new QuizAttempt(parts[0], parts[1], parts[2], parts[3],
                                      Integer.parseInt(parts[4]),
                                      Integer.parseInt(parts[5]),
                                      Double.parseDouble(parts[6]),
                                      parts[7], parts[8]);
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }
}
