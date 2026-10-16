# E-Learning Management System - Quick Start Guide

## 🚀 Get Started in 3 Steps

### Step 1: Verify Java Installation
```bash
java -version
javac -version
```
Both should show version 8 or higher.

### Step 2: Navigate to Project Directory
```bash
cd "E-Learning Management System"
```

### Step 3: Compile and Run

**macOS / Linux:**
```bash
# Make script executable
chmod +x compile.sh

# Compile and run
./compile.sh run
```

**Windows:**
```cmd
# Compile and run
compile.bat run
```

**Manual Method (All Platforms):**
```bash
javac -d bin src/model/*.java src/service/*.java src/util/*.java src/main/*.java
java -cp bin main.Main
```

---

## 📋 Test Scenario

### Scenario 1: Register and Login
1. **Choose Option 1 (Register)**
2. **Select Role 1 (Student)**
3. **Enter credentials:**
   - Username: `testuser`
   - Password: `test123`
   - Email: `test@email.com`
   - Full Name: `Test User`
4. **Choose Option 2 (Login)**
5. **Enter credentials and proceed**

### Scenario 2: Create Course (Instructor)
1. Register as **Instructor** (Role 2)
2. Login with instructor credentials
3. **Select Option 1 (Create New Course)**
4. **Enter course details:**
   - Name: `Java Basics`
   - Description: `Learn Java fundamentals`

### Scenario 3: Enroll and Take Quiz (Student)
1. Login as Student
2. **Select Option 2 (Enroll in Course)**
3. **Select Option 5 (Attempt Quiz)**
4. **Answer MCQ questions**
5. **View scores in Option 6**

---

## 📁 File Locations

All data is automatically created in the `data/` folder:
- `users.txt` - User accounts
- `courses.txt` - Courses
- `lessons.txt` - Lesson content
- `quizzes.txt` - Quiz info
- `quiz_questions.txt` - Questions
- `enrollments.txt` - Student enrollments
- `quiz_attempts.txt` - Quiz results

## ⚠️ Troubleshooting

### Java not found
- Install Java 8 or higher
- Add Java to system PATH
- Restart terminal

### Compilation errors
- Ensure all files are in correct directories
- Check for typos in file names
- Verify src folder structure

### No output / Hangs
- Check if bin directory exists
- Try manual compilation method
- Check file permissions

## 🎯 Key Features to Try

✅ User registration with different roles
✅ Secure login system
✅ Course creation and management
✅ Student enrollment system
✅ Lesson management
✅ MCQ quiz creation
✅ Quiz attempting and scoring
✅ Performance tracking

## 💡 Tips

1. **Create Admin First:** Register first user as Admin for full system access
2. **Multiple Roles:** Create different accounts to test all modules
3. **Data Persistence:** All data is saved automatically
4. **No Dependencies:** Pure Java - no external libraries needed
5. **Easy to Extend:** Well-organized code structure for future enhancements

---

**Ready to start? Run the application now and explore the E-Learning Management System!** 🎓
