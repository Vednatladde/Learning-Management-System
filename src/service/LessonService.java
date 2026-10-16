package service;

import model.Lesson;
import util.FileHandler;
import java.util.ArrayList;
import java.util.List;

/**
 * LessonService class for managing lesson operations
 */
public class LessonService {
    
    /**
     * Get all lessons from file
     */
    public static List<Lesson> getAllLessons() {
        List<Lesson> lessons = new ArrayList<>();
        List<String> lines = FileHandler.readFile(FileHandler.getLessonsFile());
        
        for (String line : lines) {
            Lesson lesson = Lesson.fromString(line);
            if (lesson != null) {
                lessons.add(lesson);
            }
        }
        return lessons;
    }

    /**
     * Get lessons by course ID
     */
    public static List<Lesson> getLessonsByCourse(String courseId) {
        List<Lesson> lessons = getAllLessons();
        List<Lesson> courseLessons = new ArrayList<>();
        
        for (Lesson lesson : lessons) {
            if (lesson.getCourseId().equals(courseId)) {
                courseLessons.add(lesson);
            }
        }
        return courseLessons;
    }

    /**
     * Get lesson by ID
     */
    public static Lesson getLessonById(String lessonId) {
        List<Lesson> lessons = getAllLessons();
        for (Lesson lesson : lessons) {
            if (lesson.getLessonId().equals(lessonId)) {
                return lesson;
            }
        }
        return null;
    }

    /**
     * Add new lesson
     */
    public static boolean addLesson(Lesson lesson) {
        FileHandler.appendToFile(FileHandler.getLessonsFile(), lesson.toString());
        return true;
    }

    /**
     * Update lesson
     */
    public static boolean updateLesson(Lesson updatedLesson) {
        List<Lesson> lessons = getAllLessons();
        List<String> lines = new ArrayList<>();
        
        boolean found = false;
        for (Lesson lesson : lessons) {
            if (lesson.getLessonId().equals(updatedLesson.getLessonId())) {
                lines.add(updatedLesson.toString());
                found = true;
            } else {
                lines.add(lesson.toString());
            }
        }
        
        if (found) {
            FileHandler.writeFile(FileHandler.getLessonsFile(), lines);
        }
        return found;
    }

    /**
     * Delete lesson by ID
     */
    public static boolean deleteLesson(String lessonId) {
        List<Lesson> lessons = getAllLessons();
        List<String> lines = new ArrayList<>();
        
        boolean found = false;
        for (Lesson lesson : lessons) {
            if (!lesson.getLessonId().equals(lessonId)) {
                lines.add(lesson.toString());
            } else {
                found = true;
            }
        }
        
        if (found) {
            FileHandler.writeFile(FileHandler.getLessonsFile(), lines);
        }
        return found;
    }

    /**
     * Generate unique lesson ID
     */
    public static String generateLessonId() {
        List<Lesson> lessons = getAllLessons();
        int maxId = 0;
        
        for (Lesson lesson : lessons) {
            try {
                int id = Integer.parseInt(lesson.getLessonId().substring(1));
                if (id > maxId) {
                    maxId = id;
                }
            } catch (NumberFormatException e) {
                // Ignore
            }
        }
        
        return "L" + (maxId + 1);
    }

    /**
     * Get total lessons for a course
     */
    public static int getTotalLessonsByCourse(String courseId) {
        return getLessonsByCourse(courseId).size();
    }
}
