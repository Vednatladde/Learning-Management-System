@echo off
REM E-Learning Management System - Installation & Deployment Setup
REM This script performs complete setup and deployment

setlocal enabledelayedexpansion

cls
echo.
echo ╔════════════════════════════════════════════════════════════╗
echo ║  E-Learning Management System - Installation ^& Deployment ║
echo ║                     Version 1.0                           ║
echo ╚════════════════════════════════════════════════════════════╝
echo.

REM Get current directory
set SCRIPT_DIR=%cd%
cd /d "%SCRIPT_DIR%"

REM Step 1: Check Java
echo [1/5] Checking Java Installation...
java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo ❌ Java is not installed!
    echo Please install Java 8 or higher from: https://www.oracle.com/java/technologies/downloads/
    pause
    exit /b 1
)

for /f "tokens=*" %%i in ('java -version 2^>^&1 ^| findstr /R "version"') do set JAVA_VERSION=%%i
echo ✅ Java installation found
echo    !JAVA_VERSION!
echo.

REM Step 2: Create directories
echo [2/5] Setting up directories...
if not exist "bin" mkdir bin
if not exist "build" mkdir build
if not exist "data" mkdir data
echo ✅ Directories created
echo.

REM Step 3: Compile
echo [3/5] Compiling Java source files...
javac -d bin src\model\*.java src\service\*.java src\util\*.java src\main\*.java
if %errorlevel% neq 0 (
    echo ❌ Compilation failed!
    pause
    exit /b 1
)
echo ✅ Compilation successful ^(20 files^)
echo.

REM Step 4: Create Manifest and JAR
echo [4/5] Creating deployable JAR package...
(
    echo Manifest-Version: 1.0
    echo Main-Class: main.Main
    echo Implementation-Title: E-Learning Management System
    echo Implementation-Version: 1.0
) > build\MANIFEST.MF

cd bin
jar cvfm ..\build\ELMS.jar ..\build\MANIFEST.MF model service util main >nul 2>&1
cd ..

if exist "build\ELMS.jar" (
    for %%i in (build\ELMS.jar) do set JAR_SIZE=%%~zi
    echo ✅ JAR package created
    echo    Size: !JAR_SIZE! bytes
) else (
    echo ❌ JAR creation failed!
    pause
    exit /b 1
)
echo.

REM Step 5: Summary
echo [5/5] Installation Complete!
echo.
echo ════════════════════════════════════════════════════════════
echo       ✅ E-Learning Management System is Ready!
echo ════════════════════════════════════════════════════════════
echo.

REM Display information
echo 📊 Deployment Information:
echo    Location: %SCRIPT_DIR%
echo    Compiled Files: bin\ ^(20 .class files^)
echo    Executable JAR: build\ELMS.jar
echo    Data Directory: data\
echo.

echo 🚀 How to Run:
echo.
echo    Option 1: Run JAR file ^(Recommended^)
echo      java -jar build\ELMS.jar
echo.
echo    Option 2: Run from compiled classes
echo      java -cp bin main.Main
echo.
echo    Option 3: Use deployment script
echo      deploy.bat
echo.

echo ❓ Would you like to start the application now? ^(y/n^)
set /p CHOICE=   > 
if /i "%CHOICE%"=="y" (
    echo.
    echo 🚀 Starting E-Learning Management System...
    echo ════════════════════════════════════════════════════════════
    echo.
    java -jar build\ELMS.jar
) else (
    echo.
    echo ✅ Installation complete! Run anytime with:
    echo    java -jar build\ELMS.jar
    echo.
)

pause
