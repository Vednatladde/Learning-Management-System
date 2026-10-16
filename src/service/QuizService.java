package service;

import model.Quiz;
import model.QuizQuestion;
import util.FileHandler;
import java.util.ArrayList;
import java.util.List;

/**
 * QuizService class for managing quiz and quiz question operations
 */
public class QuizService {
    
    /**
     * Get all quizzes from file
     */
    public static List<Quiz> getAllQuizzes() {
        List<Quiz> quizzes = new ArrayList<>();
        List<String> lines = FileHandler.readFile(FileHandler.getQuizzesFile());
        
        for (String line : lines) {
            Quiz quiz = Quiz.fromString(line);
            if (quiz != null) {
                quizzes.add(quiz);
            }
        }
        return quizzes;
    }

    /**
     * Get quiz by ID
     */
    public static Quiz getQuizById(String quizId) {
        List<Quiz> quizzes = getAllQuizzes();
        for (Quiz quiz : quizzes) {
            if (quiz.getQuizId().equals(quizId)) {
                return quiz;
            }
        }
        return null;
    }

    /**
     * Get quizzes by course ID
     */
    public static List<Quiz> getQuizzesByCourse(String courseId) {
        List<Quiz> quizzes = getAllQuizzes();
        List<Quiz> courseQuizzes = new ArrayList<>();
        
        for (Quiz quiz : quizzes) {
            if (quiz.getCourseId().equals(courseId)) {
                courseQuizzes.add(quiz);
            }
        }
        return courseQuizzes;
    }

    /**
     * Get quizzes by instructor ID
     */
    public static List<Quiz> getQuizzesByInstructor(String instructorId) {
        List<Quiz> quizzes = getAllQuizzes();
        List<Quiz> instructorQuizzes = new ArrayList<>();
        
        for (Quiz quiz : quizzes) {
            if (quiz.getInstructorId().equals(instructorId)) {
                instructorQuizzes.add(quiz);
            }
        }
        return instructorQuizzes;
    }

    /**
     * Add new quiz
     */
    public static boolean addQuiz(Quiz quiz) {
        FileHandler.appendToFile(FileHandler.getQuizzesFile(), quiz.toString());
        return true;
    }

    /**
     * Update quiz
     */
    public static boolean updateQuiz(Quiz updatedQuiz) {
        List<Quiz> quizzes = getAllQuizzes();
        List<String> lines = new ArrayList<>();
        
        boolean found = false;
        for (Quiz quiz : quizzes) {
            if (quiz.getQuizId().equals(updatedQuiz.getQuizId())) {
                lines.add(updatedQuiz.toString());
                found = true;
            } else {
                lines.add(quiz.toString());
            }
        }
        
        if (found) {
            FileHandler.writeFile(FileHandler.getQuizzesFile(), lines);
        }
        return found;
    }

    /**
     * Generate unique quiz ID
     */
    public static String generateQuizId() {
        List<Quiz> quizzes = getAllQuizzes();
        int maxId = 0;
        
        for (Quiz quiz : quizzes) {
            try {
                int id = Integer.parseInt(quiz.getQuizId().substring(1));
                if (id > maxId) {
                    maxId = id;
                }
            } catch (NumberFormatException e) {
                // Ignore
            }
        }
        
        return "Q" + (maxId + 1);
    }

    // Quiz Question Methods

    /**
     * Get all quiz questions from file
     */
    public static List<QuizQuestion> getAllQuizQuestions() {
        List<QuizQuestion> questions = new ArrayList<>();
        List<String> lines = FileHandler.readFile(FileHandler.getQuizQuestionsFile());
        
        for (String line : lines) {
            QuizQuestion question = QuizQuestion.fromString(line);
            if (question != null) {
                questions.add(question);
            }
        }
        return questions;
    }

    /**
     * Get questions by quiz ID
     */
    public static List<QuizQuestion> getQuestionsByQuiz(String quizId) {
        List<QuizQuestion> questions = getAllQuizQuestions();
        List<QuizQuestion> quizQuestions = new ArrayList<>();
        
        for (QuizQuestion question : questions) {
            if (question.getQuizId().equals(quizId)) {
                quizQuestions.add(question);
            }
        }
        return quizQuestions;
    }

    /**
     * Get question by ID
     */
    public static QuizQuestion getQuestionById(String questionId) {
        List<QuizQuestion> questions = getAllQuizQuestions();
        for (QuizQuestion question : questions) {
            if (question.getQuestionId().equals(questionId)) {
                return question;
            }
        }
        return null;
    }

    /**
     * Add new quiz question
     */
    public static boolean addQuizQuestion(QuizQuestion question) {
        FileHandler.appendToFile(FileHandler.getQuizQuestionsFile(), question.toString());
        return true;
    }

    /**
     * Generate unique question ID
     */
    public static String generateQuestionId() {
        List<QuizQuestion> questions = getAllQuizQuestions();
        int maxId = 0;
        
        for (QuizQuestion question : questions) {
            try {
                int id = Integer.parseInt(question.getQuestionId().substring(1));
                if (id > maxId) {
                    maxId = id;
                }
            } catch (NumberFormatException e) {
                // Ignore
            }
        }
        
        return "QN" + (maxId + 1);
    }

    /**
     * Get total questions in quiz
     */
    public static int getTotalQuestionsInQuiz(String quizId) {
        return getQuestionsByQuiz(quizId).size();
    }

    /**
     * Delete quiz question by ID
     */
    public static boolean deleteQuizQuestion(String questionId) {
        List<QuizQuestion> questions = getAllQuizQuestions();
        List<String> lines = new ArrayList<>();
        
        boolean found = false;
        for (QuizQuestion question : questions) {
            if (!question.getQuestionId().equals(questionId)) {
                lines.add(question.toString());
            } else {
                found = true;
            }
        }
        
        if (found) {
            FileHandler.writeFile(FileHandler.getQuizQuestionsFile(), lines);
        }
        return found;
    }

    /**
     * Delete all questions for a quiz
     */
    public static void deleteAllQuestionsByQuiz(String quizId) {
        List<QuizQuestion> questions = getAllQuizQuestions();
        List<String> lines = new ArrayList<>();
        
        for (QuizQuestion question : questions) {
            if (!question.getQuizId().equals(quizId)) {
                lines.add(question.toString());
            }
        }
        
        FileHandler.writeFile(FileHandler.getQuizQuestionsFile(), lines);
    }
}
