package service;

import model.QuizAttempt;
import util.FileHandler;
import java.util.ArrayList;
import java.util.List;

/**
 * QuizAttemptService class for managing quiz attempts
 */
public class QuizAttemptService {
    
    /**
     * Get all quiz attempts from file
     */
    public static List<QuizAttempt> getAllQuizAttempts() {
        List<QuizAttempt> attempts = new ArrayList<>();
        List<String> lines = FileHandler.readFile(FileHandler.getQuizAttemptsFile());
        
        for (String line : lines) {
            QuizAttempt attempt = QuizAttempt.fromString(line);
            if (attempt != null) {
                attempts.add(attempt);
            }
        }
        return attempts;
    }

    /**
     * Get quiz attempts by student ID
     */
    public static List<QuizAttempt> getAttemptsByStudent(String studentId) {
        List<QuizAttempt> attempts = getAllQuizAttempts();
        List<QuizAttempt> studentAttempts = new ArrayList<>();
        
        for (QuizAttempt attempt : attempts) {
            if (attempt.getStudentId().equals(studentId)) {
                studentAttempts.add(attempt);
            }
        }
        return studentAttempts;
    }

    /**
     * Get quiz attempts by quiz ID
     */
    public static List<QuizAttempt> getAttemptsByQuiz(String quizId) {
        List<QuizAttempt> attempts = getAllQuizAttempts();
        List<QuizAttempt> quizAttempts = new ArrayList<>();
        
        for (QuizAttempt attempt : attempts) {
            if (attempt.getQuizId().equals(quizId)) {
                quizAttempts.add(attempt);
            }
        }
        return quizAttempts;
    }

    /**
     * Get quiz attempts by course ID
     */
    public static List<QuizAttempt> getAttemptsByCourse(String courseId) {
        List<QuizAttempt> attempts = getAllQuizAttempts();
        List<QuizAttempt> courseAttempts = new ArrayList<>();
        
        for (QuizAttempt attempt : attempts) {
            if (attempt.getCourseId().equals(courseId)) {
                courseAttempts.add(attempt);
            }
        }
        return courseAttempts;
    }

    /**
     * Get quiz attempts by student and quiz
     */
    public static List<QuizAttempt> getAttemptsByStudentAndQuiz(String studentId, String quizId) {
        List<QuizAttempt> attempts = getAllQuizAttempts();
        List<QuizAttempt> filteredAttempts = new ArrayList<>();
        
        for (QuizAttempt attempt : attempts) {
            if (attempt.getStudentId().equals(studentId) && 
                attempt.getQuizId().equals(quizId)) {
                filteredAttempts.add(attempt);
            }
        }
        return filteredAttempts;
    }

    /**
     * Add new quiz attempt
     */
    public static boolean addQuizAttempt(QuizAttempt attempt) {
        FileHandler.appendToFile(FileHandler.getQuizAttemptsFile(), attempt.toString());
        return true;
    }

    /**
     * Generate unique attempt ID
     */
    public static String generateAttemptId() {
        List<QuizAttempt> attempts = getAllQuizAttempts();
        int maxId = 0;
        
        for (QuizAttempt attempt : attempts) {
            try {
                int id = Integer.parseInt(attempt.getAttemptId().substring(1));
                if (id > maxId) {
                    maxId = id;
                }
            } catch (NumberFormatException e) {
                // Ignore
            }
        }
        
        return "A" + (maxId + 1);
    }

    /**
     * Get high score for a student in a quiz
     */
    public static QuizAttempt getHighestScoreAttempt(String studentId, String quizId) {
        List<QuizAttempt> attempts = getAttemptsByStudentAndQuiz(studentId, quizId);
        
        if (attempts.isEmpty()) {
            return null;
        }
        
        QuizAttempt highest = attempts.get(0);
        for (QuizAttempt attempt : attempts) {
            if (attempt.getScore() > highest.getScore()) {
                highest = attempt;
            }
        }
        return highest;
    }

    /**
     * Get average score for a student in a course
     */
    public static double getAverageScoreByCourse(String studentId, String courseId) {
        List<QuizAttempt> attempts = getAttemptsByCourse(courseId);
        double total = 0;
        int count = 0;
        
        for (QuizAttempt attempt : attempts) {
            if (attempt.getStudentId().equals(studentId)) {
                total += attempt.getPercentage();
                count++;
            }
        }
        
        if (count == 0) {
            return 0;
        }
        return total / count;
    }

    /**
     * Get quiz attempt by ID
     */
    public static QuizAttempt getAttemptById(String attemptId) {
        List<QuizAttempt> attempts = getAllQuizAttempts();
        
        for (QuizAttempt attempt : attempts) {
            if (attempt.getAttemptId().equals(attemptId)) {
                return attempt;
            }
        }
        return null;
    }
}
