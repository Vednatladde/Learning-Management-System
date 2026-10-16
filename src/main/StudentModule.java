package main;

import model.Course;
import model.Lesson;
import model.Quiz;
import model.QuizQuestion;
import model.EnrolledCourse;
import model.QuizAttempt;
import service.AuthenticationService;
import service.CourseService;
import service.LessonService;
import service.QuizService;
import service.EnrollmentService;
import service.QuizAttemptService;
import util.InputHandler;
import java.time.LocalDate;
import java.util.List;

/**
 * StudentModule class for handling student operations
 */
public class StudentModule {
    
    /**
     * Display student menu
     */
    public static void displayStudentMenu() {
        while (AuthenticationService.isStudent()) {
            InputHandler.displayHeader("STUDENT DASHBOARD");
            System.out.println("1. View Available Courses");
            System.out.println("2. Enroll in Course");
            System.out.println("3. View Enrolled Courses");
            System.out.println("4. View Lessons");
            System.out.println("5. Attempt Quiz");
            System.out.println("6. View Quiz Scores");
            System.out.println("7. Logout");
            System.out.println("==========================================");
            
            int choice = InputHandler.readInteger("Enter your choice: ");
            
            switch (choice) {
                case 1:
                    viewAvailableCourses();
                    break;
                case 2:
                    enrollInCourse();
                    break;
                case 3:
                    viewEnrolledCourses();
                    break;
                case 4:
                    viewLessons();
                    break;
                case 5:
                    attemptQuiz();
                    break;
                case 6:
                    viewQuizScores();
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
     * View available courses
     */
    private static void viewAvailableCourses() {
        InputHandler.displayHeader("AVAILABLE COURSES");
        
        List<Course> courses = CourseService.getAllCourses();
        
        if (courses.isEmpty()) {
            InputHandler.displayMessage("No courses available.");
            return;
        }
        
        System.out.println("Total Courses: " + courses.size());
        System.out.println("==========================================");
        
        for (int i = 0; i < courses.size(); i++) {
            Course course = courses.get(i);
            System.out.println((i + 1) + ". " + course.getCourseName());
            System.out.println("   Course ID: " + course.getCourseId());
            System.out.println("   Description: " + course.getDescription());
            System.out.println("   Instructor: " + course.getInstructorName());
            System.out.println("   Students Enrolled: " + course.getEnrollmentCount());
            System.out.println("------------------------------------------");
        }
    }

    /**
     * Enroll in course
     */
    private static void enrollInCourse() {
        InputHandler.displayHeader("ENROLL IN COURSE");
        
        String studentId = AuthenticationService.getCurrentUser().getUserId();
        List<Course> courses = CourseService.getAllCourses();
        
        if (courses.isEmpty()) {
            InputHandler.displayError("No courses available!");
            return;
        }
        
        System.out.println("Select a course to enroll:");
        for (int i = 0; i < courses.size(); i++) {
            System.out.println((i + 1) + ". " + courses.get(i).getCourseName());
        }
        
        int courseChoice = InputHandler.readInteger("Enter course number: ");
        
        if (courseChoice < 1 || courseChoice > courses.size()) {
            InputHandler.displayError("Invalid selection!");
            return;
        }
        
        Course selectedCourse = courses.get(courseChoice - 1);
        
        // Check if already enrolled
        if (EnrollmentService.isEnrolled(studentId, selectedCourse.getCourseId())) {
            InputHandler.displayError("You are already enrolled in this course!");
            return;
        }
        
        // Create enrollment
        String enrollmentId = EnrollmentService.generateEnrollmentId();
        String enrollmentDate = LocalDate.now().toString();
        
        EnrolledCourse enrollment = new EnrolledCourse(enrollmentId, studentId, 
                                                       selectedCourse.getCourseId(),
                                                       selectedCourse.getCourseName(),
                                                       enrollmentDate, "ACTIVE");
        
        if (EnrollmentService.addEnrollment(enrollment)) {
            InputHandler.displaySuccess("Successfully enrolled in " + selectedCourse.getCourseName());
        } else {
            InputHandler.displayError("Failed to enroll in course!");
        }
    }

    /**
     * View enrolled courses
     */
    private static void viewEnrolledCourses() {
        InputHandler.displayHeader("MY ENROLLED COURSES");
        
        String studentId = AuthenticationService.getCurrentUser().getUserId();
        List<EnrolledCourse> enrollments = EnrollmentService.getEnrollmentsByStudent(studentId);
        
        if (enrollments.isEmpty()) {
            InputHandler.displayMessage("You have not enrolled in any courses yet.");
            return;
        }
        
        System.out.println("Total Enrolled Courses: " + enrollments.size());
        System.out.println("==========================================");
        
        for (int i = 0; i < enrollments.size(); i++) {
            EnrolledCourse enrollment = enrollments.get(i);
            System.out.println((i + 1) + ". " + enrollment.getCourseName());
            System.out.println("   Course ID: " + enrollment.getCourseId());
            System.out.println("   Enrollment Date: " + enrollment.getEnrollmentDate());
            System.out.println("   Status: " + enrollment.getStatus());
            System.out.println("------------------------------------------");
        }
    }

    /**
     * View lessons
     */
    private static void viewLessons() {
        InputHandler.displayHeader("VIEW LESSONS");
        
        String studentId = AuthenticationService.getCurrentUser().getUserId();
        List<EnrolledCourse> enrollments = EnrollmentService.getEnrollmentsByStudent(studentId);
        
        if (enrollments.isEmpty()) {
            InputHandler.displayError("You have not enrolled in any courses!");
            return;
        }
        
        System.out.println("Select a course:");
        for (int i = 0; i < enrollments.size(); i++) {
            System.out.println((i + 1) + ". " + enrollments.get(i).getCourseName());
        }
        
        int courseChoice = InputHandler.readInteger("Enter course number: ");
        
        if (courseChoice < 1 || courseChoice > enrollments.size()) {
            InputHandler.displayError("Invalid selection!");
            return;
        }
        
        EnrolledCourse selectedEnrollment = enrollments.get(courseChoice - 1);
        String courseId = selectedEnrollment.getCourseId();
        
        List<Lesson> lessons = LessonService.getLessonsByCourse(courseId);
        
        if (lessons.isEmpty()) {
            InputHandler.displayMessage("No lessons available for this course.");
            return;
        }
        
        System.out.println("\nLessons for: " + selectedEnrollment.getCourseName());
        System.out.println("Total Lessons: " + lessons.size());
        System.out.println("==========================================");
        
        for (int i = 0; i < lessons.size(); i++) {
            Lesson lesson = lessons.get(i);
            System.out.println((i + 1) + ". " + lesson.getLessonTitle());
            System.out.println("   Lesson ID: " + lesson.getLessonId());
            System.out.println("   Created: " + lesson.getCreatedDate());
            System.out.println("------------------------------------------");
        }
        
        int lessonChoice = InputHandler.readInteger("Select lesson to view (0 to go back): ");
        
        if (lessonChoice == 0) {
            return;
        }
        
        if (lessonChoice < 1 || lessonChoice > lessons.size()) {
            InputHandler.displayError("Invalid selection!");
            return;
        }
        
        Lesson selectedLesson = lessons.get(lessonChoice - 1);
        displayLessonContent(selectedLesson);
    }

    /**
     * Display lesson content
     */
    private static void displayLessonContent(Lesson lesson) {
        InputHandler.displayHeader("LESSON: " + lesson.getLessonTitle());
        System.out.println(lesson.getLessonContent());
        System.out.println("==========================================");
    }

    /**
     * Attempt quiz
     */
    private static void attemptQuiz() {
        InputHandler.displayHeader("ATTEMPT QUIZ");
        
        String studentId = AuthenticationService.getCurrentUser().getUserId();
        List<EnrolledCourse> enrollments = EnrollmentService.getEnrollmentsByStudent(studentId);
        
        if (enrollments.isEmpty()) {
            InputHandler.displayError("You have not enrolled in any courses!");
            return;
        }
        
        System.out.println("Select a course:");
        for (int i = 0; i < enrollments.size(); i++) {
            System.out.println((i + 1) + ". " + enrollments.get(i).getCourseName());
        }
        
        int courseChoice = InputHandler.readInteger("Enter course number: ");
        
        if (courseChoice < 1 || courseChoice > enrollments.size()) {
            InputHandler.displayError("Invalid selection!");
            return;
        }
        
        String courseId = enrollments.get(courseChoice - 1).getCourseId();
        List<Quiz> quizzes = QuizService.getQuizzesByCourse(courseId);
        
        if (quizzes.isEmpty()) {
            InputHandler.displayMessage("No quizzes available for this course.");
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
        conductQuiz(studentId, selectedQuiz, courseId);
    }

    /**
     * Conduct quiz and calculate score
     */
    private static void conductQuiz(String studentId, Quiz quiz, String courseId) {
        InputHandler.displayHeader("QUIZ: " + quiz.getQuizTitle());
        System.out.println(quiz.getQuizDescription());
        System.out.println("==========================================");
        
        List<QuizQuestion> questions = QuizService.getQuestionsByQuiz(quiz.getQuizId());
        
        if (questions.isEmpty()) {
            InputHandler.displayError("No questions in this quiz!");
            return;
        }
        
        int score = 0;
        int totalQuestions = questions.size();
        
        for (int i = 0; i < questions.size(); i++) {
            QuizQuestion question = questions.get(i);
            
            System.out.println("\nQuestion " + (i + 1) + " of " + totalQuestions);
            System.out.println(question.getQuestionText());
            System.out.println("------------------------------------------");
            
            List<String> options = question.getOptions();
            for (int j = 0; j < options.size(); j++) {
                System.out.println((j + 1) + ". " + options.get(j));
            }
            
            int answer = InputHandler.readInteger("Your answer (1-4): ");
            
            if (answer >= 1 && answer <= 4) {
                if ((answer - 1) == question.getCorrectOptionIndex()) {
                    score++;
                    InputHandler.displaySuccess("Correct!");
                } else {
                    InputHandler.displayError("Wrong! Correct answer was: " + 
                                            (question.getCorrectOptionIndex() + 1));
                }
            } else {
                InputHandler.displayError("Invalid option!");
            }
        }
        
        // Calculate percentage
        double percentage = (score * 100.0) / totalQuestions;
        String status = percentage >= 50 ? "PASSED" : "FAILED";
        
        // Save quiz attempt
        String attemptId = QuizAttemptService.generateAttemptId();
        String attemptDate = LocalDate.now().toString();
        
        QuizAttempt attempt = new QuizAttempt(attemptId, studentId, quiz.getQuizId(),
                                              courseId, score, totalQuestions,
                                              percentage, attemptDate, status);
        
        if (QuizAttemptService.addQuizAttempt(attempt)) {
            InputHandler.displaySeparator();
            System.out.println("QUIZ COMPLETED!");
            System.out.println("Score: " + score + " / " + totalQuestions);
            System.out.println("Percentage: " + String.format("%.2f", percentage) + "%");
            System.out.println("Status: " + status);
            System.out.println("==========================================");
        } else {
            InputHandler.displayError("Failed to save quiz attempt!");
        }
    }

    /**
     * View quiz scores
     */
    private static void viewQuizScores() {
        InputHandler.displayHeader("MY QUIZ SCORES");
        
        String studentId = AuthenticationService.getCurrentUser().getUserId();
        List<QuizAttempt> attempts = QuizAttemptService.getAttemptsByStudent(studentId);
        
        if (attempts.isEmpty()) {
            InputHandler.displayMessage("You have not attempted any quizzes yet.");
            return;
        }
        
        System.out.println("Total Quiz Attempts: " + attempts.size());
        System.out.println("==========================================");
        
        for (QuizAttempt attempt : attempts) {
            System.out.println("Quiz ID: " + attempt.getQuizId());
            System.out.println("Score: " + attempt.getScore() + " / " + attempt.getTotalQuestions());
            System.out.println("Percentage: " + String.format("%.2f", attempt.getPercentage()) + "%");
            System.out.println("Status: " + attempt.getStatus());
            System.out.println("Attempt Date: " + attempt.getAttemptDate());
            System.out.println("------------------------------------------");
        }
    }
}
