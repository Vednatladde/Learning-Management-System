# E-Learning Management System - Architecture & Design

## 📐 System Architecture

### Layered Architecture

```
┌─────────────────────────────────────────────┐
│           Presentation Layer                 │
│  (AdminModule, InstructorModule,            │
│   StudentModule, Main)                       │
├─────────────────────────────────────────────┤
│           Business Logic Layer               │
│  (Service Classes - *Service.java)           │
├─────────────────────────────────────────────┤
│            Data Access Layer                 │
│  (FileHandler, Model Classes)                │
├─────────────────────────────────────────────┤
│         Persistence Layer                    │
│  (Text Files in data/ directory)             │
└─────────────────────────────────────────────┘
```

## 🏗️ Class Diagram Overview

### Model Classes (Data Entities)
```
User
├─ userId: String
├─ username: String
├─ password: String
├─ role: String (ADMIN, INSTRUCTOR, STUDENT)
├─ email: String
├─ fullName: String
└─ joinDate: String

Course
├─ courseId: String
├─ courseName: String
├─ description: String
├─ instructorId: String
├─ instructorName: String
├─ createdDate: String
└─ enrollmentCount: int

Lesson
├─ lessonId: String
├─ courseId: String
├─ lessonTitle: String
├─ lessonContent: String
└─ createdDate: String

Quiz
├─ quizId: String
├─ courseId: String
├─ quizTitle: String
├─ quizDescription: String
├─ instructorId: String
├─ createdDate: String
├─ totalQuestions: int
└─ questions: List<QuizQuestion>

QuizQuestion
├─ questionId: String
├─ quizId: String
├─ questionText: String
├─ options: List<String>
└─ correctOptionIndex: int

EnrolledCourse
├─ enrollmentId: String
├─ studentId: String
├─ courseId: String
├─ courseName: String
├─ enrollmentDate: String
└─ status: String (ACTIVE, COMPLETED, DROPPED)

QuizAttempt
├─ attemptId: String
├─ studentId: String
├─ quizId: String
├─ courseId: String
├─ score: int
├─ totalQuestions: int
├─ percentage: double
├─ attemptDate: String
└─ status: String (PASSED, FAILED, COMPLETED)
```

## 🔧 Service Classes

### AuthenticationService
- `register()` - Register new user
- `login()` - Authenticate user
- `logout()` - End user session
- `isLoggedIn()` - Check login status
- `getCurrentUser()` - Get active user
- `isAdmin()`, `isInstructor()`, `isStudent()` - Role checks

### UserService
- `getAllUsers()` - Fetch all users
- `getUserById()` - Get user by ID
- `getUserByUsername()` - Get user by username
- `addUser()` - Create new user
- `updateUser()` - Modify user data
- `deleteUser()` - Remove user
- `generateUserId()` - Create unique ID
- `getUsersByRole()` - Filter by role

### CourseService
- `getAllCourses()` - Fetch all courses
- `getCourseById()` - Get specific course
- `getCoursesByInstructor()` - Get instructor's courses
- `addCourse()` - Create course
- `updateCourse()` - Modify course
- `deleteCourse()` - Remove course
- `generateCourseId()` - Create unique ID

### LessonService
- `getAllLessons()` - Fetch all lessons
- `getLessonsByCourse()` - Get course lessons
- `addLesson()` - Create lesson
- `updateLesson()` - Modify lesson
- `deleteLesson()` - Remove lesson
- `generateLessonId()` - Create unique ID

### QuizService
- `getAllQuizzes()` - Fetch all quizzes
- `getQuizzesByCourse()` - Get course quizzes
- `getQuizzesByInstructor()` - Get instructor's quizzes
- `addQuiz()` - Create quiz
- `updateQuiz()` - Modify quiz
- `addQuizQuestion()` - Add question to quiz
- `getQuestionsByQuiz()` - Get quiz questions

### EnrollmentService
- `getAllEnrollments()` - Fetch all enrollments
- `getEnrollmentsByStudent()` - Get student enrollments
- `getEnrollmentsByCourse()` - Get course enrollments
- `isEnrolled()` - Check if enrolled (prevent duplicates)
- `addEnrollment()` - Create enrollment
- `generateEnrollmentId()` - Create unique ID

### QuizAttemptService
- `getAllQuizAttempts()` - Fetch all attempts
- `getAttemptsByStudent()` - Get student's attempts
- `getAttemptsByQuiz()` - Get quiz's attempts
- `addQuizAttempt()` - Save attempt with score
- `getHighestScoreAttempt()` - Get best score
- `getAverageScoreByCourse()` - Calculate average

## 🎯 Module Classes

### AdminModule
**Responsibilities:**
- View all users
- Add instructors
- Remove users
- Manage courses
- View system statistics

### InstructorModule
**Responsibilities:**
- Create courses
- Add lessons
- Create quizzes with MCQ questions
- View created content
- Monitor student progress

### StudentModule
**Responsibilities:**
- View available courses
- Enroll in courses
- View lessons
- Attempt quizzes
- Track quiz scores

## 💾 Data Persistence Strategy

### File Format
All data uses pipe-separated format for easy parsing:
```
Field1|Field2|Field3|Field4|...
```

### Example Entries

**User:** `U1|john_doe|pass123|STUDENT|john@email.com|John Doe|2026-01-29`

**Course:** `C1|Java 101|Learn Java basics|U2|Jane Instructor|2026-01-20|45`

**Lesson:** `L1|C1|Introduction to Java|Content here|2026-01-20`

**Quiz:** `Q1|C1|Java Basics Quiz|Test your knowledge|U2|2026-01-21|5`

**Enrollment:** `E1|U1|C1|Java 101|2026-01-22|ACTIVE`

## 🔄 Data Flow

### User Registration Flow
```
User Input → AuthenticationService.register()
           → UserService.generateUserId()
           → UserService.addUser()
           → FileHandler.appendToFile()
           → Confirmation Message
```

### Course Enrollment Flow
```
Student Selection → EnrollmentService.isEnrolled() (check duplicate)
                  → EnrollmentService.generateEnrollmentId()
                  → EnrollmentService.addEnrollment()
                  → FileHandler.appendToFile()
                  → Success Confirmation
```

### Quiz Attempt Flow
```
Student Answers → Calculate Score
               → QuizAttemptService.generateAttemptId()
               → QuizAttemptService.addQuizAttempt()
               → FileHandler.appendToFile()
               → Display Results
```

## 🛡️ Key Design Principles

### 1. Separation of Concerns
- Model classes handle data representation
- Service classes handle business logic
- Module classes handle user interaction

### 2. Single Responsibility
Each class has one primary responsibility:
- Services manage specific entities
- Utilities handle file/input operations
- Modules manage user workflows

### 3. DRY (Don't Repeat Yourself)
- Common operations in service classes
- Reusable utility functions
- Centralized file handling

### 4. Code Reusability
- Service methods called from multiple places
- Helper functions in InputHandler
- Consistent ID generation strategy

## 🔐 Security Features

### Implemented
- Username uniqueness validation
- Password-based authentication
- Duplicate enrollment prevention
- Role-based access control

### Recommended for Production
- Password hashing (BCrypt)
- Encryption for sensitive data
- Database with SQL injection prevention
- Audit logging
- Session management
- Rate limiting

## 📊 Data Validation

### User Registration
- ✓ Username must be unique
- ✓ Role must be valid
- ✓ Email format validation (recommended)
- ✓ Password strength enforcement (recommended)

### Course Enrollment
- ✓ Course must exist
- ✓ Student must be logged in
- ✓ Duplicate enrollment prevention
- ✓ Course ID validation

### Quiz Submission
- ✓ Valid options selection
- ✓ Score calculation validation
- ✓ Question count verification

## 🚀 Performance Considerations

### Current Implementation
- Linear search for lookups (O(n))
- ArrayList for collections
- Text file I/O

### Optimization Opportunities
- Implement HashMap for O(1) lookups
- Batch file operations
- Caching frequently accessed data
- Index-based searching

### Scalability
- Current: Suitable for ~10,000 users
- With DB: Scalable to millions
- With caching: Handle concurrent access

## 🧪 Testing Strategy

### Unit Testing
- Test each service independently
- Verify CRUD operations
- Validate business logic

### Integration Testing
- Test module interactions
- Verify data persistence
- Validate workflows

### System Testing
- Full end-to-end scenarios
- User role verification
- Data integrity checks

## 📈 Future Architecture Enhancements

### Short Term
- Add logging framework (Log4j)
- Implement caching layer
- Add input validation framework

### Medium Term
- Database migration (MySQL/PostgreSQL)
- RESTful API layer
- Web frontend (React/Angular)

### Long Term
- Microservices architecture
- Cloud deployment
- Distributed caching
- Message queues for async operations

---

**Architecture Design**: Layered with Service-oriented approach
**Patterns Used**: MVC, Factory, Singleton
**Database**: File-based (Text files with structured format)
**Scalability**: Designed for academic use; database recommended for production
