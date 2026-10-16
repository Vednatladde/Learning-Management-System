package main;

import model.Course;
import model.Lesson;
import model.Quiz;
import model.QuizQuestion;
import service.AuthenticationService;
import service.CourseService;
import service.LessonService;
import service.QuizService;
import util.InputHandler;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * InstructorModule class for handling instructor operations
 */
public class InstructorModule {
    
    /**
     * Display instructor menu
     */
    public static void displayInstructorMenu() {
        while (AuthenticationService.isInstructor()) {
            InputHandler.displayHeader("INSTRUCTOR DASHBOARD");
            System.out.println("1. Create New Course");
            System.out.println("2. View My Courses");
            System.out.println("3. Add Lesson to Course");
            System.out.println("4. Create Quiz");
            System.out.println("5. View My Quizzes");
            System.out.println("6. View Quiz Results");
            System.out.println("7. Logout");
            System.out.println("==========================================");
            
            int choice = InputHandler.readInteger("Enter your choice: ");
            
            switch (choice) {
                case 1:
                    createNewCourse();
                    break;
                case 2:
                    viewMyCourses();
                    break;
                case 3:
                    addLessonToCourse();
                    break;
                case 4:
                    createQuiz();
                    break;
                case 5:
                    viewMyQuizzes();
                    break;
                case 6:
                    viewQuizResults();
                    break;
                case 7:
                    AuthenticationService.logout();
                    return;
                default:
                    InputHandler.displayError("Invalid choice!");
            }
            
            InputHandler.pressEnter();
        }
    }

    /**
     * Create new course
     */
    private static void createNewCourse() {
        InputHandler.displayHeader("CREATE NEW COURSE");
        
        String courseName = InputHandler.readString("Enter course name: ");
        String description = InputHandler.readString("Enter course description: ");
        
        String courseId = CourseService.generateCourseId();
        String instructorId = AuthenticationService.getCurrentUser().getUserId();
        String instructorName = AuthenticationService.getCurrentUser().getFullName();
        String createdDate = LocalDate.now().toString();
        
        Course course = new Course(courseId, courseName, description, instructorId, 
                                   instructorName, createdDate);
        
        if (CourseService.addCourse(course)) {
            InputHandler.displaySuccess("Course created successfully! Course ID: " + courseId);
        } else {
            InputHandler.displayError("Failed to create course!");
        }
    }

    /**
     * View my courses
     */
    private static void viewMyCourses() {
        InputHandler.displayHeader("MY COURSES");
        
        String instructorId = AuthenticationService.getCurrentUser().getUserId();
        List<Course> courses = CourseService.getCoursesByInstructor(instructorId);
        
        if (courses.isEmpty()) {
            InputHandler.displayMessage("You have not created any courses yet.");
            return;
        }
        
        System.out.println("Total Courses: " + courses.size());
        System.out.println("==========================================");
        
        for (int i = 0; i < courses.size(); i++) {
            Course course = courses.get(i);
            System.out.println((i + 1) + ". " + course.getCourseName());
            System.out.println("   Course ID: " + course.getCourseId());
            System.out.println("   Description: " + course.getDescription());
            System.out.println("   Created: " + course.getCreatedDate());
            System.out.println("   Students Enrolled: " + course.getEnrollmentCount());
            System.out.println("------------------------------------------");
        }
    }

    /**
     * Add lesson to course
     */
    private static void addLessonToCourse() {
        InputHandler.displayHeader("ADD LESSON TO COURSE");
        
        String instructorId = AuthenticationService.getCurrentUser().getUserId();
        List<Course> courses = CourseService.getCoursesByInstructor(instructorId);
        
        if (courses.isEmpty()) {
            InputHandler.displayError("You have not created any courses yet!");
            return;
        }
        
        System.out.println("Select a course:");
        for (int i = 0; i < courses.size(); i++) {
            System.out.println((i + 1) + ". " + courses.get(i).getCourseName());
        }
        
        int courseChoice = InputHandler.readInteger("Enter course number: ");
        
        if (courseChoice < 1 || courseChoice > courses.size()) {
            InputHandler.displayError("Invalid selection!");
            return;
        }
        
        Course selectedCourse = courses.get(courseChoice - 1);
        
        String lessonTitle = InputHandler.readString("Enter lesson title: ");
        String lessonContent = InputHandler.readString("Enter lesson content: ");
        
        String lessonId = LessonService.generateLessonId();
        String createdDate = LocalDate.now().toString();
        
        Lesson lesson = new Lesson(lessonId, selectedCourse.getCourseId(), 
                                   lessonTitle, lessonContent, createdDate);
        
        if (LessonService.addLesson(lesson)) {
            InputHandler.displaySuccess("Lesson added successfully!");
        } else {
            InputHandler.displayError("Failed to add lesson!");
        }
    }

    /**
     * Create quiz
     */
    private static void createQuiz() {
        InputHandler.displayHeader("CREATE QUIZ");
        
        String instructorId = AuthenticationService.getCurrentUser().getUserId();
        List<Course> courses = CourseService.getCoursesByInstructor(instructorId);
        
        if (courses.isEmpty()) {
            InputHandler.displayError("You have not created any courses yet!");
            return;
        }
        
        System.out.println("Select a course:");
        for (int i = 0; i < courses.size(); i++) {
            System.out.println((i + 1) + ". " + courses.get(i).getCourseName());
        }
        
        int courseChoice = InputHandler.readInteger("Enter course number: ");
        
        if (courseChoice < 1 || courseChoice > courses.size()) {
            InputHandler.displayError("Invalid selection!");
            return;
        }
        
        Course selectedCourse = courses.get(courseChoice - 1);
        
        String quizTitle = InputHandler.readString("Enter quiz title: ");
        String quizDescription = InputHandler.readString("Enter quiz description: ");
        
        String quizId = QuizService.generateQuizId();
        String createdDate = LocalDate.now().toString();
        
        Quiz quiz = new Quiz(quizId, selectedCourse.getCourseId(), quizTitle, 
                             quizDescription, instructorId, createdDate);
        
        if (QuizService.addQuiz(quiz)) {
            InputHandler.displaySuccess("Quiz created successfully! Quiz ID: " + quizId);
            
            // Add questions to quiz
            addQuestionsToQuiz(quizId);
        } else {
            InputHandler.displayError("Failed to create quiz!");
        }
    }

    /**
     * Add questions to quiz
     */
    private static void addQuestionsToQuiz(String quizId) {
        while (true) {
            InputHandler.displayMessage("\n" + "=== ADD QUESTION ===");
            String questionText = InputHandler.readString("Enter question text (or 'done' to finish): ");
            
            if (questionText.equalsIgnoreCase("done")) {
                break;
            }
            
            String questionId = QuizService.generateQuestionId();
            QuizQuestion question = new QuizQuestion(questionId, quizId, questionText);
            
            // Add options
            System.out.println("Enter 4 options:");
            for (int i = 1; i <= 4; i++) {
                String option = InputHandler.readString("Option " + i + ": ");
                question.addOption(option);
            }
            
            int correctAnswer = InputHandler.readInteger("Enter correct option number (1-4): ");
            if (correctAnswer < 1 || correctAnswer > 4) {
                InputHandler.displayError("Invalid option number!");
                continue;
            }
            
            question.setCorrectOptionIndex(correctAnswer - 1);
            
            if (QuizService.addQuizQuestion(question)) {
                InputHandler.displaySuccess("Question added successfully!");
                
                // Update quiz question count
                Quiz quiz = QuizService.getQuizById(quizId);
                quiz.addQuestion(question);
                QuizService.updateQuiz(quiz);
            } else {
                InputHandler.displayError("Failed to add question!");
            }
        }
    }

    /**
     * View my quizzes
     */
    private static void viewMyQuizzes() {
        InputHandler.displayHeader("MY QUIZZES");
        
        String instructorId = AuthenticationService.getCurrentUser().getUserId();
        List<Quiz> quizzes = QuizService.getQuizzesByInstructor(instructorId);
        
        if (quizzes.isEmpty()) {
            InputHandler.displayMessage("You have not created any quizzes yet.");
            return;
        }
        
        System.out.println("Total Quizzes: " + quizzes.size());
        System.out.println("==========================================");
        
        for (Quiz quiz : quizzes) {
            System.out.println("Quiz ID: " + quiz.getQuizId());
            System.out.println("Title: " + quiz.getQuizTitle());
            System.out.println("Course ID: " + quiz.getCourseId());
            System.out.println("Total Questions: " + quiz.getTotalQuestions());
            System.out.println("Created: " + quiz.getCreatedDate());
            System.out.println("------------------------------------------");
        }
    }

    /**
     * View quiz results
     */
    private static void viewQuizResults() {
        InputHandler.displayHeader("VIEW QUIZ RESULTS");
        
        String instructorId = AuthenticationService.getCurrentUser().getUserId();
        List<Quiz> quizzes = QuizService.getQuizzesByInstructor(instructorId);
        
        if (quizzes.isEmpty()) {
            InputHandler.displayError("You have not created any quizzes yet!");
            return;
        }
        
        System.out.println("Select a quiz:");
        for (int i = 0; i < quizzes.size(); i++) {
            System.out.println((i + 1) + ". " + quizzes.get(i).getQuizTitle());
        }
        
        int quizChoice = InputHandler.readInteger("Enter quiz number: ");
        
        if (quizChoice < 1 || quizChoice > quizzes.size()) {
            InputHandler.displayError("Invalid selection!");
            return;
        }
        
        Quiz selectedQuiz = quizzes.get(quizChoice - 1);
        
        System.out.println("\nQuiz Results for: " + selectedQuiz.getQuizTitle());
        System.out.println("==========================================");
        
        // Note: This would require access to QuizAttemptService
        InputHandler.displayMessage("Quiz results functionality to be implemented.");
    }
}
