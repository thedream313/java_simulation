#!/bin/bash

# Set Java home
JAVA_HOME="/Users/dumitrubernic/.sdkman/candidates/java/21.0.1-tem"
JAVAC="$JAVA_HOME/bin/javac"
JAVA="$JAVA_HOME/bin/java"

# Create bin directory
mkdir -p bin

# Compile all Java files
$JAVAC -d bin $(find src -name "*.java")

# Run the application
$JAVA -cp bin Main 