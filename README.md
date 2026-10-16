# 🎓 E-Learning Management System (ELMS)

<div align="center">

![GitHub repo size](https://img.shields.io/github/repo-size/Vednatladde/Learning-Management-System?style=for-the-badge&logo=github&color=blue)
![License](https://img.shields.io/badge/License-MIT-green.svg?style=for-the-badge)
![Java](https://img.shields.io/badge/Java-8%2B-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Frontend](https://img.shields.io/badge/Web_UI-HTML5%20%2F%20CSS3%20%2F%20JS-E34F26?style=for-the-badge&logo=html5&logoColor=white)
![Architecture](https://img.shields.io/badge/Architecture-Modular%20Layered%20MVC-6C5CE7?style=for-the-badge)
![Platform](https://img.shields.io/badge/Platform-Windows%20%7C%20macOS%20%7C%20Linux-0984E3?style=for-the-badge)

**A complete, production-grade E-Learning Management System featuring a pure Java modular engine, role-based access control, file persistence, and an interactive modern web UI suite.**

[Key Features](#-key-features) • [System Architecture](#-system-architecture) • [Web Portals](#-interactive-web-portals) • [Quick Start](#-quick-start) • [Installation & Setup](#-installation--setup) • [Author](#-author) • [License](#-license)

</div>

---

## 📖 Overview

The **E-Learning Management System (ELMS)** is a comprehensive learning and educational administration platform. Engineered with clean architectural patterns in standard Java, it delivers end-to-end functionality for three distinct user roles: **Students**, **Instructors**, and **Administrators**.

ELMS comes equipped with:
1. **Core Java Console Engine**: Highly robust, fast, dependency-free Java backend executing complete business logic and data persistence.
2. **Interactive Modern Web Dashboards**: Responsive, glassmorphic web UI interfaces for visual course authoring, student learning, and administrative oversight.
3. **Flat-File Database Persistence**: Zero-setup, reliable text-based persistence engine using pipe-delimited schemas for immediate portability.
4. **Pre-packaged Deployment**: One-click compilation scripts and standalone executable JAR packaging (`ELMS.jar`).

---

## ✨ Key Features

### 👑 Administrator Module
- **System Statistics & Metrics**: Real-time overview of total registered users, active courses, enrollment figures, and system activity.
- **User Governance**: Full administrative control over user accounts — create, manage, and remove instructor and student profiles.
- **Course Catalog Management**: Global oversight of all courses created across the platform with moderation capabilities.

### 👨‍🏫 Instructor Module
- **Course Authoring**: Create and publish courses with detailed syllabi, descriptions, and metadata.
- **Lesson Management**: Build and organize text-based lesson modules mapped to individual courses.
- **MCQ Quiz Engine**: Dynamic quiz builder with multiple-choice questions, customizable option sets, and designated correct answer keys.
- **Grading & Assessment Analytics**: Monitor student quiz attempts, review scores, and evaluate performance trends.

### 🎓 Student Module
- **Course Discovery & Enrollment**: Browse available courses and enroll instantly with automatic duplicate-enrollment prevention.
- **Self-Paced Learning Reader**: Access structured lessons and course reading materials in a distraction-free interface.
- **Interactive Quiz Taking**: Attempt timed/untimed multiple-choice assessments with immediate score calculation.
- **Performance History**: Review past quiz attempts, historical scores, and personal learning progress.

### 🛡️ Authentication & Security
- **Role-Based Access Control (RBAC)**: Strict role boundary separation between Admin, Instructor, and Student capabilities.
- **Account Registration & Login**: User registration workflows with credential verification and session management.

---

## 🏗️ System Architecture

The application adopts a **3-Tier Layered Architecture** following MVC and Service-Oriented design principles:

```
Learning-Management-System/
├── 📁 src/
│   ├── 📁 model/                  # Data Entity Models
│   │   ├── User.java              # User entity (Student, Instructor, Admin)
│   │   ├── Course.java            # Course structure & metadata
│   │   ├── Lesson.java            # Modular lesson materials
│   │   ├── Quiz.java              # Quiz definition
│   │   ├── QuizQuestion.java      # Multiple-choice question structure
│   │   ├── EnrolledCourse.java    # Student enrollment relationship
│   │   └── QuizAttempt.java       # Student quiz assessment records
│   ├── 📁 service/                # Business Logic Layer
│   │   ├── AuthenticationService  # Login, registration, role checks
│   │   ├── UserService.java       # User account lifecycle logic
│   │   ├── CourseService.java     # Course creation & queries
│   │   ├── LessonService.java     # Lesson management logic
│   │   ├── QuizService.java       # Quiz generation & scoring engine
│   │   ├── EnrollmentService.java # Enrollment rules & duplicate prevention
│   │   └── QuizAttemptService     # Score computation & history
│   ├── 📁 util/                   # Common Utilities
│   │   ├── FileHandler.java       # I/O handler for flat-file persistence
│   │   └── InputHandler.java      # Interactive CLI input validator
│   └── 📁 main/                   # Presentation / Module Layer
│       ├── Main.java              # Main application entry point
│       ├── AdminModule.java       # Admin dashboard CLI workflow
│       ├── InstructorModule.java  # Instructor dashboard CLI workflow
│       └── StudentModule.java     # Student dashboard CLI workflow
├── 📁 web-ui/                     # Modern Web User Interfaces
│   ├── admin-dashboard.html       # Visual admin management console
│   ├── course-builder.html        # Drag-and-drop course & quiz creator
│   ├── student-learning-portal.html # Student learning workspace
│   └── README.md                  # Web UI documentation
├── 📁 data/                       # Persistence Data Store
│   ├── users.txt                  # User accounts and credentials
│   ├── courses.txt                # Course records
│   ├── lessons.txt                # Lesson contents
│   ├── quizzes.txt                # Quiz definitions
│   ├── quiz_questions.txt         # Question bank & answer keys
│   ├── enrollments.txt            # Course enrollment mappings
│   └── quiz_attempts.txt          # Quiz submissions and scores
├── 📁 build/                      # Build Artifacts
│   ├── ELMS.jar                   # Pre-compiled standalone executable JAR
│   └── MANIFEST.MF                # Main-Class manifest specification
├── 📁 bin/                        # Compiled bytecode output directory
├── compile.bat / compile.sh       # One-click build scripts
├── deploy.bat / deploy.sh         # One-click deploy & run scripts
├── install.bat / install.sh       # Automated installation scripts
├── ARCHITECTURE.md                # In-depth architectural specification
├── API_REFERENCE.md               # Complete API and class documentation
├── DEPLOYMENT_GUIDE.md            # Comprehensive deployment manual
├── QUICKSTART.md                  # 3-minute rapid onboarding guide
└── LICENSE                        # MIT License
```

---

## 💻 Interactive Web Portals

In addition to the console-based Java engine, this repository provides modern, responsive HTML5/CSS3/JavaScript web portals located in `web-ui/`:

| Portal | File | Target Role | Key Capabilities |
|---|---|---|---|
| 👑 **Admin Console** | [`admin-dashboard.html`](web-ui/admin-dashboard.html) | System Admin | User tables, platform metrics, course auditing, activity feed |
| 👨‍🏫 **Course Studio** | [`course-builder.html`](web-ui/course-builder.html) | Instructor | Dynamic curriculum builder, lesson creator, MCQ authoring |
| 🎓 **Student Portal** | [`student-learning-portal.html`](web-ui/student-learning-portal.html) | Student | Enrolled course cards, lesson viewer, interactive quiz UI |

*To run the web portals, open any of the HTML files directly in your web browser or serve them with any static web server.*

---

## ⚡ Quick Start

### 1. Run the Pre-compiled Executable JAR
If you already have Java (JRE/JDK 8+) installed, you can run ELMS immediately:

```bash
java -jar build/ELMS.jar
```

### 2. One-Click Compile & Run

**On Windows:**
```cmd
compile.bat run
```

**On macOS / Linux:**
```bash
chmod +x compile.sh
./compile.sh run
```

---

## 🚀 Installation & Setup

### Prerequisites
- **Java Development Kit (JDK)**: Version 8, 11, 17, or 21+
- **Git** (optional, for cloning)
- Any modern web browser (for viewing `web-ui/`)

### Clone the Repository
```bash
git clone https://github.com/Vednatladde/Learning-Management-System.git
cd Learning-Management-System
```

### Manual Compilation
```bash
# Create bin directory
mkdir bin

# Compile all modules
javac -d bin src/model/*.java src/service/*.java src/util/*.java src/main/*.java

# Run the application
java -cp bin main.Main
```

### Building the Standalone JAR
```bash
# Package compiled classes into an executable JAR
jar cvfe build/ELMS.jar main.Main -C bin .
```

---

## 📊 Default Test Accounts

For testing and demonstration, you can use or register the following accounts:

| Role | Username | Password | Access Level |
|---|---|---|---|
| **Administrator** | `admin` | `admin123` | Full system access, user & course moderation |
| **Instructor** | `instructor1` | `pass123` | Course creation, lesson upload, quiz authoring |
| **Student** | `student1` | `pass123` | Course enrollment, lesson reading, quiz taking |

*(You can also register any new account directly from the application start menu).*

---

## 📚 Technical Documentation

- 📐 **[ARCHITECTURE.md](ARCHITECTURE.md)**: System design patterns, class relationships, and data flow.
- 📖 **[API_REFERENCE.md](API_REFERENCE.md)**: Full API documentation for all 20 Java classes and service methods.
- 🚀 **[QUICKSTART.md](QUICKSTART.md)**: Fast-track developer guide with step-by-step test scenarios.
- 📦 **[DEPLOYMENT_GUIDE.md](DEPLOYMENT_GUIDE.md)**: Production deployment instructions, script details, and troubleshooting.

---

## 👤 Author

**Vedant Ladde**
- GitHub: [@Vednatladde](https://github.com/Vednatladde)
- Repository: [Learning-Management-System](https://github.com/Vednatladde/Learning-Management-System)

---

## 📜 License

This project is licensed under the **MIT License** — see the [LICENSE](LICENSE) file for details.

```
Copyright (c) 2026 Vedant Ladde
```
