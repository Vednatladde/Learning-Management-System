package util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * FileHandler class for reading and writing data to files
 * Handles persistence of users, courses, lessons, quizzes, enrollments, and quiz attempts
 */
public class FileHandler {
    private static final String DATA_DIR = "data/";
    private static final String USERS_FILE = DATA_DIR + "users.txt";
    private static final String COURSES_FILE = DATA_DIR + "courses.txt";
    private static final String LESSONS_FILE = DATA_DIR + "lessons.txt";
    private static final String QUIZZES_FILE = DATA_DIR + "quizzes.txt";
    private static final String QUIZ_QUESTIONS_FILE = DATA_DIR + "quiz_questions.txt";
    private static final String ENROLLMENTS_FILE = DATA_DIR + "enrollments.txt";
    private static final String QUIZ_ATTEMPTS_FILE = DATA_DIR + "quiz_attempts.txt";

    static {
        // Create data directory if it doesn't exist
        File dataDir = new File(DATA_DIR);
        if (!dataDir.exists()) {
            dataDir.mkdir();
        }
    }

    /**
     * Read all lines from a file
     */
    public static List<String> readFile(String filename) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    lines.add(line);
                }
            }
        } catch (FileNotFoundException e) {
            // File doesn't exist yet, return empty list
        } catch (IOException e) {
            System.out.println("Error reading file: " + filename);
        }
        return lines;
    }

    /**
     * Write lines to a file
     */
    public static void writeFile(String filename, List<String> lines) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + filename);
        }
    }

    /**
     * Append a line to a file
     */
    public static void appendToFile(String filename, String line) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(line);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error appending to file: " + filename);
        }
    }

    // File path getters
    public static String getUsersFile() {
        return USERS_FILE;
    }

    public static String getCoursesFile() {
        return COURSES_FILE;
    }

    public static String getLessonsFile() {
        return LESSONS_FILE;
    }

    public static String getQuizzesFile() {
        return QUIZZES_FILE;
    }

    public static String getQuizQuestionsFile() {
        return QUIZ_QUESTIONS_FILE;
    }

    public static String getEnrollmentsFile() {
        return ENROLLMENTS_FILE;
    }

    public static String getQuizAttemptsFile() {
        return QUIZ_ATTEMPTS_FILE;
    }

    /**
     * Check if a file exists
     */
    public static boolean fileExists(String filename) {
        File file = new File(filename);
        return file.exists();
    }

    /**
     * Clear a file
     */
    public static void clearFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            // File will be cleared when opened in write mode
        } catch (IOException e) {
            System.out.println("Error clearing file: " + filename);
        }
    }
}
