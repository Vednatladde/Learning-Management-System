package service;

import model.Course;
import util.FileHandler;
import java.util.ArrayList;
import java.util.List;

/**
 * CourseService class for managing course operations
 */
public class CourseService {
    
    /**
     * Get all courses from file
     */
    public static List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();
        List<String> lines = FileHandler.readFile(FileHandler.getCoursesFile());
        
        for (String line : lines) {
            Course course = Course.fromString(line);
            if (course != null) {
                courses.add(course);
            }
        }
        return courses;
    }

    /**
     * Get course by ID
     */
    public static Course getCourseById(String courseId) {
        List<Course> courses = getAllCourses();
        for (Course course : courses) {
            if (course.getCourseId().equals(courseId)) {
                return course;
            }
        }
        return null;
    }

    /**
     * Get courses by instructor ID
     */
    public static List<Course> getCoursesByInstructor(String instructorId) {
        List<Course> courses = getAllCourses();
        List<Course> instructorCourses = new ArrayList<>();
        
        for (Course course : courses) {
            if (course.getInstructorId().equals(instructorId)) {
                instructorCourses.add(course);
            }
        }
        return instructorCourses;
    }

    /**
     * Add new course
     */
    public static boolean addCourse(Course course) {
        FileHandler.appendToFile(FileHandler.getCoursesFile(), course.toString());
        return true;
    }

    /**
     * Update course
     */
    public static boolean updateCourse(Course updatedCourse) {
        List<Course> courses = getAllCourses();
        List<String> lines = new ArrayList<>();
        
        boolean found = false;
        for (Course course : courses) {
            if (course.getCourseId().equals(updatedCourse.getCourseId())) {
                lines.add(updatedCourse.toString());
                found = true;
            } else {
                lines.add(course.toString());
            }
        }
        
        if (found) {
            FileHandler.writeFile(FileHandler.getCoursesFile(), lines);
        }
        return found;
    }

    /**
     * Delete course by ID
     */
    public static boolean deleteCourse(String courseId) {
        List<Course> courses = getAllCourses();
        List<String> lines = new ArrayList<>();
        
        boolean found = false;
        for (Course course : courses) {
            if (!course.getCourseId().equals(courseId)) {
                lines.add(course.toString());
            } else {
                found = true;
            }
        }
        
        if (found) {
            FileHandler.writeFile(FileHandler.getCoursesFile(), lines);
        }
        return found;
    }

    /**
     * Generate unique course ID
     */
    public static String generateCourseId() {
        List<Course> courses = getAllCourses();
        int maxId = 0;
        
        for (Course course : courses) {
            try {
                int id = Integer.parseInt(course.getCourseId().substring(1));
                if (id > maxId) {
                    maxId = id;
                }
            } catch (NumberFormatException e) {
                // Ignore
            }
        }
        
        return "C" + (maxId + 1);
    }

    /**
     * Get total courses
     */
    public static int getTotalCourses() {
        return getAllCourses().size();
    }

    /**
     * Get course name by ID
     */
    public static String getCourseNameById(String courseId) {
        Course course = getCourseById(courseId);
        if (course != null) {
            return course.getCourseName();
        }
        return null;
    }
}
