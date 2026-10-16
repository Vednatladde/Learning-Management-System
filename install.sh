#!/bin/bash

# E-Learning Management System - Installation & Deployment Setup
# This script performs complete setup and deployment

set -e  # Exit on error

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

# Banner
clear
echo -e "${BLUE}"
echo "╔════════════════════════════════════════════════════════════╗"
echo "║  E-Learning Management System - Installation & Deployment ║"
echo "║                     Version 1.0                           ║"
echo "╚════════════════════════════════════════════════════════════╝"
echo -e "${NC}"
echo ""

# Get script directory
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
cd "$SCRIPT_DIR"

# Step 1: Check Java
echo -e "${YELLOW}[1/5] Checking Java Installation...${NC}"
if ! command -v java &> /dev/null; then
    echo -e "${RED}❌ Java is not installed!${NC}"
    echo "Please install Java 8 or higher from: https://www.oracle.com/java/technologies/downloads/"
    exit 1
fi

JAVA_VERSION=$(java -version 2>&1 | grep -oP 'version "\K[^"]*' | head -1)
JAVA_MAJOR=$(echo $JAVA_VERSION | cut -d'.' -f1)

if [ "$JAVA_MAJOR" -lt 8 ]; then
    echo -e "${RED}❌ Java version too old (requires 8+)${NC}"
    exit 1
fi

echo -e "${GREEN}✅ Java $JAVA_VERSION found${NC}"
echo ""

# Step 2: Create directories
echo -e "${YELLOW}[2/5] Setting up directories...${NC}"
mkdir -p bin build data
echo -e "${GREEN}✅ Directories created${NC}"
echo ""

# Step 3: Compile
echo -e "${YELLOW}[3/5] Compiling Java source files...${NC}"
javac -d bin src/model/*.java src/service/*.java src/util/*.java src/main/*.java 2>&1
if [ $? -ne 0 ]; then
    echo -e "${RED}❌ Compilation failed!${NC}"
    exit 1
fi
echo -e "${GREEN}✅ Compilation successful (20 files)${NC}"
echo ""

# Step 4: Create JAR
echo -e "${YELLOW}[4/5] Creating deployable JAR package...${NC}"
cat > build/MANIFEST.MF << 'EOF'
Manifest-Version: 1.0
Main-Class: main.Main
Implementation-Title: E-Learning Management System
Implementation-Version: 1.0
EOF

cd bin
jar cvfm ../build/ELMS.jar ../build/MANIFEST.MF model service util main > /dev/null 2>&1
cd ..

if [ -f "build/ELMS.jar" ]; then
    JAR_SIZE=$(du -h build/ELMS.jar | cut -f1)
    echo -e "${GREEN}✅ JAR package created (${JAR_SIZE})${NC}"
else
    echo -e "${RED}❌ JAR creation failed!${NC}"
    exit 1
fi
echo ""

# Step 5: Summary
echo -e "${YELLOW}[5/5] Installation Complete!${NC}"
echo ""
echo -e "${GREEN}════════════════════════════════════════════════════════════${NC}"
echo -e "${GREEN}      ✅ E-Learning Management System is Ready!${NC}"
echo -e "${GREEN}════════════════════════════════════════════════════════════${NC}"
echo ""

# Display deployment information
echo -e "${BLUE}📊 Deployment Information:${NC}"
echo "  Location: $SCRIPT_DIR"
echo "  Java Version: $JAVA_VERSION"
echo "  Compiled Files: bin/ (20 .class files)"
echo "  Executable JAR: build/ELMS.jar"
echo "  Data Directory: data/"
echo ""

# Display run instructions
echo -e "${BLUE}🚀 How to Run:${NC}"
echo ""
echo "  Option 1: Run JAR file (Recommended)"
echo "    ${GREEN}java -jar build/ELMS.jar${NC}"
echo ""
echo "  Option 2: Run from compiled classes"
echo "    ${GREEN}java -cp bin main.Main${NC}"
echo ""
echo "  Option 3: Use deployment script"
echo "    ${GREEN}./deploy.sh${NC}"
echo ""

# Prompt to run
echo -e "${BLUE}❓ Would you like to start the application now? (y/n)${NC}"
read -p "  > " -n 1 -r
echo ""

if [[ $REPLY =~ ^[Yy]$ ]]; then
    echo ""
    echo -e "${YELLOW}🚀 Starting E-Learning Management System...${NC}"
    echo -e "${BLUE}════════════════════════════════════════════════════════════${NC}"
    echo ""
    java -jar build/ELMS.jar
else
    echo ""
    echo -e "${GREEN}✅ Installation complete! Run anytime with:${NC}"
    echo "   ${GREEN}java -jar build/ELMS.jar${NC}"
    echo ""
fi
