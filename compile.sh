#!/bin/bash

# Compilation Script for E-Learning Management System
# This script compiles and runs the ELMS application

echo "=========================================="
echo "E-Learning Management System - Build Script"
echo "=========================================="

# Create bin directory if it doesn't exist
if [ ! -d "bin" ]; then
    echo "Creating bin directory..."
    mkdir -p bin
fi

# Compile all Java files
echo "Compiling Java files..."
javac -d bin src/model/*.java src/service/*.java src/util/*.java src/main/*.java

if [ $? -eq 0 ]; then
    echo "Compilation successful!"
    echo ""
    echo "To run the application, use:"
    echo "java -cp bin main.Main"
    echo ""
    echo "Or run this script with the 'run' parameter:"
    echo "./compile.sh run"
else
    echo "Compilation failed!"
    exit 1
fi

# If 'run' parameter is provided, run the application
if [ "$1" == "run" ]; then
    echo "Starting application..."
    echo "=========================================="
    java -cp bin main.Main
fi
