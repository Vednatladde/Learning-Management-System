package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * QuizQuestion class representing a multiple choice question in a quiz
 */
public class QuizQuestion implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String questionId;
    private String quizId;
    private String questionText;
    private List<String> options;
    private int correctOptionIndex;

    // Constructor
    public QuizQuestion(String questionId, String quizId, String questionText) {
        this.questionId = questionId;
        this.quizId = quizId;
        this.questionText = questionText;
        this.options = new ArrayList<>();
        this.correctOptionIndex = -1;
    }

    // Getters and Setters
    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getQuizId() {
        return quizId;
    }

    public void setQuizId(String quizId) {
        this.quizId = quizId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public void addOption(String option) {
        this.options.add(option);
    }

    public int getCorrectOptionIndex() {
        return correctOptionIndex;
    }

    public void setCorrectOptionIndex(int correctOptionIndex) {
        this.correctOptionIndex = correctOptionIndex;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(questionId).append("|").append(quizId).append("|")
          .append(questionText).append("|");
        
        for (int i = 0; i < options.size(); i++) {
            sb.append(options.get(i));
            if (i < options.size() - 1) {
                sb.append("~");
            }
        }
        sb.append("|").append(correctOptionIndex);
        
        return sb.toString();
    }

    // Parse quiz question from string format
    public static QuizQuestion fromString(String data) {
        String[] parts = data.split("\\|");
        if (parts.length >= 5) {
            QuizQuestion question = new QuizQuestion(parts[0], parts[1], parts[2]);
            
            String[] options = parts[3].split("~");
            for (String option : options) {
                question.addOption(option);
            }
            
            try {
                question.setCorrectOptionIndex(Integer.parseInt(parts[4]));
            } catch (NumberFormatException e) {
                question.setCorrectOptionIndex(-1);
            }
            
            return question;
        }
        return null;
    }
}
