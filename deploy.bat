@echo off
REM E-Learning Management System - Deployment Script for Windows
REM This script compiles and runs the application

setlocal enabledelayedexpansion

cls
echo.
echo ╔════════════════════════════════════════════════════════════╗
echo ║     E-Learning Management System - Local Deployment       ║
echo ║                    Version 1.0                            ║
echo ╚════════════════════════════════════════════════════════════╝
echo.

REM Get current directory
set PROJECT_DIR=%cd%
echo 📁 Project Directory: %PROJECT_DIR%
echo 🔍 Checking Java installation...

REM Check if Java is installed
java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo ❌ Java is not installed!
    echo Please install Java 8 or higher
    pause
    exit /b 1
)

REM Get Java version
for /f "tokens=*" %%i in ('java -version 2^>^&1 ^| findstr /R "version"') do set JAVA_VERSION=%%i
echo ✅ Java installed: %JAVA_VERSION%
echo.

REM Create bin directory
echo 📁 Creating build directory...
if not exist "bin" mkdir bin

REM Compile Java files
echo 🔨 Compiling Java files...
javac -d bin src\model\*.java src\service\*.java src\util\*.java src\main\*.java

if %errorlevel% neq 0 (
    echo ❌ Compilation failed!
    pause
    exit /b 1
)

echo ✅ Compilation successful!
echo.

REM Create build directory
if not exist "build" mkdir build

REM Create MANIFEST file
echo 📦 Creating deployable JAR package...
(
    echo Manifest-Version: 1.0
    echo Main-Class: main.Main
    echo Created-By: ELMS Deployment Script
) > build\MANIFEST.MF

REM Create JAR file
cd bin
jar cvfm ..\build\ELMS.jar ..\build\MANIFEST.MF model service util main >nul 2>&1
cd ..

if exist "build\ELMS.jar" (
    echo ✅ JAR file created: build\ELMS.jar
    echo.
    
    REM Show file information
    for %%i in (build\ELMS.jar) do set JAR_SIZE=%%~zi bytes
    echo 📊 Package Information:
    echo    Size: !JAR_SIZE!
    echo    Location: %PROJECT_DIR%\build\ELMS.jar
    echo.
    
    REM Run the application
    echo 🚀 Starting application...
    echo ════════════════════════════════════════════════════════════
    echo.
    java -jar build\ELMS.jar
) else (
    echo ❌ Failed to create JAR file
    pause
    exit /b 1
)

pause
