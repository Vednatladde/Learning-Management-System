# E-Learning Management System - API Reference & Class Documentation

## 📚 Complete Class Reference

---

## Model Classes

### User.java
```java
public class User implements Serializable

// Constructor
public User(String userId, String username, String password, String role, 
            String email, String fullName, String joinDate)

// Key Methods
public String getUserId()
public String getUsername()
public String getPassword()
public String getRole()          // ADMIN, INSTRUCTOR, STUDENT
public String getEmail()
public String getFullName()
public String getJoinDate()
public String toString()          // Returns pipe-separated format
public static User fromString(String data)  // Parse from file
```

### Course.java
```java
public class Course implements Serializable

// Constructor
public Course(String courseId, String courseName, String description, 
              String instructorId, String instructorName, String createdDate)

// Key Methods
public String getCourseId()
public String getCourseName()
public String getDescription()
public String getInstructorId()
public String getInstructorName()
public String getCreatedDate()
public int getEnrollmentCount()
public void setEnrollmentCount(int count)
public String toString()
public static Course fromString(String data)
```

### Lesson.java
```java
public class Lesson implements Serializable

// Constructor
public Lesson(String lessonId, String courseId, String lessonTitle, 
              String lessonContent, String createdDate)

// Key Methods
public String getLessonId()
public String getCourseId()
public String getLessonTitle()
public String getLessonContent()
public String getCreatedDate()
public String toString()
public static Lesson fromString(String data)
```

### Quiz.java
```java
public class Quiz implements Serializable

// Constructor
public Quiz(String quizId, String courseId, String quizTitle, 
            String quizDescription, String instructorId, String createdDate)

// Key Methods
public String getQuizId()
public String getCourseId()
public String getQuizTitle()
public String getQuizDescription()
public String getInstructorId()
public int getTotalQuestions()
public void setTotalQuestions(int count)
public List<QuizQuestion> getQuestions()
public void addQuestion(QuizQuestion question)
public String toString()
public static Quiz fromString(String data)
```

### QuizQuestion.java
```java
public class QuizQuestion implements Serializable

// Constructor
public QuizQuestion(String questionId, String quizId, String questionText)

// Key Methods
public String getQuestionId()
public String getQuizId()
public String getQuestionText()
public List<String> getOptions()
public void addOption(String option)
public int getCorrectOptionIndex()
public void setCorrectOptionIndex(int index)
public String toString()
public static QuizQuestion fromString(String data)
```

### EnrolledCourse.java
```java
public class EnrolledCourse implements Serializable

// Constructor
public EnrolledCourse(String enrollmentId, String studentId, String courseId,
                      String courseName, String enrollmentDate, String status)

// Key Methods
public String getEnrollmentId()
public String getStudentId()
public String getCourseId()
public String getCourseName()
public String getEnrollmentDate()
public String getStatus()         // ACTIVE, COMPLETED, DROPPED
public String toString()
public static EnrolledCourse fromString(String data)
```

### QuizAttempt.java
```java
public class QuizAttempt implements Serializable

// Constructor
public QuizAttempt(String attemptId, String studentId, String quizId,
                   String courseId, int score, int totalQuestions,
                   double percentage, String attemptDate, String status)

// Key Methods
public String getAttemptId()
public String getStudentId()
public String getQuizId()
public String getCourseId()
public int getScore()
public int getTotalQuestions()
public double getPercentage()
public String getAttemptDate()
public String getStatus()         // PASSED, FAILED, COMPLETED
public String toString()
public static QuizAttempt fromString(String data)
```

---

## Service Classes

### AuthenticationService.java

```java
public class AuthenticationService

// Registration
public static boolean register(String username, String password, String role,
                              String email, String fullName)
// Login
public static boolean login(String username, String password)

// Session Management
public static void logout()
public static User getCurrentUser()
public static boolean isLoggedIn()
public static String getCurrentUserRole()

// Role Checks
public static boolean isAdmin()
public static boolean isInstructor()
public static boolean isStudent()
```

### UserService.java

```java
public class UserService

// Read Operations
public static List<User> getAllUsers()
public static User getUserByUsername(String username)
public static User getUserById(String userId)
public static List<User> getUsersByRole(String role)
public static boolean usernameExists(String username)

// Write Operations
public static boolean addUser(User user)
public static boolean updateUser(User updatedUser)
public static boolean deleteUser(String userId)

// Utility
public static String generateUserId()
```

### CourseService.java

```java
public class CourseService

// Read Operations
public static List<Course> getAllCourses()
public static Course getCourseById(String courseId)
public static List<Course> getCoursesByInstructor(String instructorId)
public static int getTotalCourses()
public static String getCourseNameById(String courseId)

// Write Operations
public static boolean addCourse(Course course)
public static boolean updateCourse(Course updatedCourse)
public static boolean deleteCourse(String courseId)

// Utility
public static String generateCourseId()
```

### LessonService.java

```java
public class LessonService

// Read Operations
public static List<Lesson> getAllLessons()
public static List<Lesson> getLessonsByCourse(String courseId)
public static Lesson getLessonById(String lessonId)
public static int getTotalLessonsByCourse(String courseId)

// Write Operations
public static boolean addLesson(Lesson lesson)
public static boolean updateLesson(Lesson updatedLesson)
public static boolean deleteLesson(String lessonId)

// Utility
public static String generateLessonId()
```

### QuizService.java

```java
public class QuizService

// Quiz Operations
public static List<Quiz> getAllQuizzes()
public static Quiz getQuizById(String quizId)
public static List<Quiz> getQuizzesByCourse(String courseId)
public static List<Quiz> getQuizzesByInstructor(String instructorId)
public static boolean addQuiz(Quiz quiz)
public static boolean updateQuiz(Quiz updatedQuiz)
public static String generateQuizId()

// Question Operations
public static List<QuizQuestion> getAllQuizQuestions()
public static List<QuizQuestion> getQuestionsByQuiz(String quizId)
public static QuizQuestion getQuestionById(String questionId)
public static boolean addQuizQuestion(QuizQuestion question)
public static boolean deleteQuizQuestion(String questionId)
public static void deleteAllQuestionsByQuiz(String quizId)
public static String generateQuestionId()

// Utility
public static int getTotalQuestionsInQuiz(String quizId)
```

### EnrollmentService.java

```java
public class EnrollmentService

// Read Operations
public static List<EnrolledCourse> getAllEnrollments()
public static List<EnrolledCourse> getEnrollmentsByStudent(String studentId)
public static List<EnrolledCourse> getEnrollmentsByCourse(String courseId)
public static EnrolledCourse getEnrollmentById(String enrollmentId)
public static boolean isEnrolled(String studentId, String courseId)
public static int getTotalEnrolledStudents(String courseId)

// Write Operations
public static boolean addEnrollment(EnrolledCourse enrollment)

// Utility
public static String generateEnrollmentId()
```

### QuizAttemptService.java

```java
public class QuizAttemptService

// Read Operations
public static List<QuizAttempt> getAllQuizAttempts()
public static List<QuizAttempt> getAttemptsByStudent(String studentId)
public static List<QuizAttempt> getAttemptsByQuiz(String quizId)
public static List<QuizAttempt> getAttemptsByCourse(String courseId)
public static List<QuizAttempt> getAttemptsByStudentAndQuiz(String studentId, String quizId)
public static QuizAttempt getAttemptById(String attemptId)

// Write Operations
public static boolean addQuizAttempt(QuizAttempt attempt)

// Analysis
public static QuizAttempt getHighestScoreAttempt(String studentId, String quizId)
public static double getAverageScoreByCourse(String studentId, String courseId)

// Utility
public static String generateAttemptId()
```

---

## Utility Classes

### FileHandler.java

```java
public class FileHandler

// File Operations
public static List<String> readFile(String filename)
public static void writeFile(String filename, List<String> lines)
public static void appendToFile(String filename, String line)
public static boolean fileExists(String filename)
public static void clearFile(String filename)

// File Path Constants
public static String getUsersFile()
public static String getCoursesFile()
public static String getLessonsFile()
public static String getQuizzesFile()
public static String getQuizQuestionsFile()
public static String getEnrollmentsFile()
public static String getQuizAttemptsFile()
```

### InputHandler.java

```java
public class InputHandler

// Input Methods
public static String readString(String prompt)
public static int readInteger(String prompt)
public static double readDouble(String prompt)

// Display Methods
public static void displayMessage(String message)
public static void displayError(String message)
public static void displaySuccess(String message)
public static void displaySeparator()
public static void displayHeader(String title)

// Control Flow
public static void pressEnter()
public static void closeScanner()
```

---

## Module Classes

### AdminModule.java

```java
public class AdminModule

// Menu
public static void displayAdminMenu()

// Operations
private static void viewAllUsers()
private static void addNewInstructor()
private static void removeUser()
private static void viewAllCourses()
private static void deleteCourse()
private static void viewSystemStatistics()
```

### InstructorModule.java

```java
public class InstructorModule

// Menu
public static void displayInstructorMenu()

// Operations
private static void createNewCourse()
private static void viewMyCourses()
private static void addLessonToCourse()
private static void createQuiz()
private static void addQuestionsToQuiz(String quizId)
private static void viewMyQuizzes()
private static void viewQuizResults()
```

### StudentModule.java

```java
public class StudentModule

// Menu
public static void displayStudentMenu()

// Operations
private static void viewAvailableCourses()
private static void enrollInCourse()
private static void viewEnrolledCourses()
private static void viewLessons()
private static void displayLessonContent(Lesson lesson)
private static void attemptQuiz()
private static void conductQuiz(String studentId, Quiz quiz, String courseId)
private static void viewQuizScores()
```

### Main.java

```java
public class Main

// Entry Point
public static void main(String[] args)

// Menus
private static void displayWelcome()
private static void mainMenu()
private static void registerMenu()
private static void loginMenu()
private static void dashboardMenu()
private static void exitApplication()
```

---

## Common Usage Patterns

### Pattern 1: Get All Entities
```java
List<User> users = UserService.getAllUsers();
List<Course> courses = CourseService.getAllCourses();
List<Lesson> lessons = LessonService.getAllLessons();
```

### Pattern 2: Search by ID
```java
User user = UserService.getUserById("U1");
Course course = CourseService.getCourseById("C1");
```

### Pattern 3: Filter by Criteria
```java
List<Course> instructorCourses = CourseService.getCoursesByInstructor("U2");
List<EnrolledCourse> studentEnrollments = EnrollmentService.getEnrollmentsByStudent("U3");
```

### Pattern 4: Create Entity
```java
User user = new User(userId, username, password, role, email, fullName, date);
UserService.addUser(user);

String courseId = CourseService.generateCourseId();
Course course = new Course(courseId, name, desc, instrId, instrName, date);
CourseService.addCourse(course);
```

### Pattern 5: Update Entity
```java
user.setEmail("newemail@example.com");
UserService.updateUser(user);

course.setEnrollmentCount(25);
CourseService.updateCourse(course);
```

### Pattern 6: Delete Entity
```java
UserService.deleteUser("U1");
CourseService.deleteCourse("C1");
```

### Pattern 7: Check Status
```java
if (EnrollmentService.isEnrolled(studentId, courseId)) {
    System.out.println("Already enrolled");
}

if (AuthenticationService.isStudent()) {
    // Show student menu
}
```

---

## Data Format Reference

### Pipe-Separated Format (|)

**User Format:**
```
UserID|Username|Password|Role|Email|FullName|JoinDate
Example: U1|john_doe|pass123|STUDENT|john@example.com|John Doe|2026-01-29
```

**Course Format:**
```
CourseID|CourseName|Description|InstructorID|InstructorName|CreatedDate|EnrollmentCount
Example: C1|Java101|Learn Java|U2|Jane Doe|2026-01-20|15
```

**Lesson Format:**
```
LessonID|CourseID|Title|Content|CreatedDate
Example: L1|C1|Intro|This is content|2026-01-20
```

**Quiz Format:**
```
QuizID|CourseID|Title|Description|InstructorID|CreatedDate|TotalQuestions
Example: Q1|C1|Quiz1|Java basics|U2|2026-01-21|5
```

**Question Format:**
```
QuestionID|QuizID|QuestionText|Options~Separated~By~Tildes|CorrectOptionIndex
Example: QN1|Q1|What is Java?|Language~Framework~Tool~IDE|0
```

**Enrollment Format:**
```
EnrollmentID|StudentID|CourseID|CourseName|EnrollmentDate|Status
Example: E1|U1|C1|Java101|2026-01-22|ACTIVE
```

**Quiz Attempt Format:**
```
AttemptID|StudentID|QuizID|CourseID|Score|TotalQuestions|Percentage|Date|Status
Example: A1|U1|Q1|C1|4|5|80.0|2026-01-25|PASSED
```

---

## Error Handling

### Common Error Messages

| Error | Cause | Solution |
|-------|-------|----------|
| "Username already exists!" | Duplicate username | Choose unique username |
| "User not found!" | Invalid username/ID | Check credentials |
| "Invalid password!" | Wrong password | Re-enter correct password |
| "No courses available!" | No courses created | Create courses as instructor |
| "Already enrolled in course!" | Duplicate enrollment | Cannot re-enroll |
| "Invalid selection!" | Out of range option | Select from available options |
| "Failed to create course!" | File I/O error | Check disk space and permissions |

---

## Performance Tips

1. **Faster Searches**: Store frequently accessed data in maps
2. **Batch Operations**: Combine multiple file operations
3. **Lazy Loading**: Load data only when needed
4. **Caching**: Store results of expensive operations

---

## Extension Points

### Adding New Features

1. **Create Model Class**
   - Implement Serializable
   - Add toString() and fromString()

2. **Create Service Class**
   - Add CRUD methods
   - Add search/filter methods
   - Use FileHandler for persistence

3. **Update Modules**
   - Add menu options
   - Call new service methods
   - Handle user input

4. **Update Main Menu**
   - Add new role if needed
   - Route to new module

---

**Last Updated**: January 2026
**Version**: 1.0
**API Status**: Stable ✓
