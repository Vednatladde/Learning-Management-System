@echo off
REM Compilation Script for E-Learning Management System
REM This script compiles and runs the ELMS application for Windows

echo.
echo ==========================================
echo E-Learning Management System - Build Script
echo ==========================================
echo.

REM Create bin directory if it doesn't exist
if not exist "bin" (
    echo Creating bin directory...
    mkdir bin
)

REM Compile all Java files
echo Compiling Java files...
javac -d bin src\model\*.java src\service\*.java src\util\*.java src\main\*.java

if %errorlevel% equ 0 (
    echo Compilation successful!
    echo.
    echo To run the application, use:
    echo java -cp bin main.Main
    echo.
    echo Or run this script with the 'run' parameter:
    echo compile.bat run
) else (
    echo Compilation failed!
    exit /b 1
)

REM If 'run' parameter is provided, run the application
if "%1"=="run" (
    echo Starting application...
    echo ==========================================
    java -cp bin main.Main
)

pause
