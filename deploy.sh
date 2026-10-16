#!/bin/bash

# E-Learning Management System - Deployment Script
# This script compiles and runs the application

echo "╔════════════════════════════════════════════════════════════╗"
echo "║     E-Learning Management System - Local Deployment       ║"
echo "║                    Version 1.0                            ║"
echo "╚════════════════════════════════════════════════════════════╝"
echo ""

# Set project directory
PROJECT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
cd "$PROJECT_DIR"

echo "📁 Project Directory: $PROJECT_DIR"
echo "🔍 Checking Java installation..."

# Check if Java is installed
if ! command -v java &> /dev/null; then
    echo "❌ Java is not installed!"
    echo "Please install Java 8 or higher"
    exit 1
fi

JAVA_VERSION=$(java -version 2>&1 | grep -oP 'version "\K[^"]*' | head -1)
echo "✅ Java Version: $JAVA_VERSION"
echo ""

# Create bin directory
echo "📁 Creating build directory..."
mkdir -p bin

# Compile Java files
echo "🔨 Compiling Java files..."
javac -d bin src/model/*.java src/service/*.java src/util/*.java src/main/*.java 2>&1

if [ $? -ne 0 ]; then
    echo "❌ Compilation failed!"
    exit 1
fi

echo "✅ Compilation successful!"
echo ""

# Create MANIFEST file for JAR
echo "📦 Creating deployable JAR package..."
mkdir -p build

cat > build/MANIFEST.MF << 'EOF'
Manifest-Version: 1.0
Main-Class: main.Main
Created-By: ELMS Deployment Script
EOF

# Create JAR file
cd bin
jar cvfm ../build/ELMS.jar ../build/MANIFEST.MF model service util main > /dev/null 2>&1
cd ..

if [ -f "build/ELMS.jar" ]; then
    echo "✅ JAR file created: build/ELMS.jar"
    echo ""
    
    # Show file information
    JAR_SIZE=$(du -h build/ELMS.jar | cut -f1)
    echo "📊 Package Information:"
    echo "   Size: $JAR_SIZE"
    echo "   Location: $PROJECT_DIR/build/ELMS.jar"
    echo ""
    
    # Run the application
    echo "🚀 Starting application..."
    echo "════════════════════════════════════════════════════════════"
    echo ""
    java -jar build/ELMS.jar
else
    echo "❌ Failed to create JAR file"
    exit 1
fi
