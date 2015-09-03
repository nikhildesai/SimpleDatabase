# SimpleDatabase
Simple in-memory database implemented using Java

# Pre-requisites

You need to have JDK7+ installed. Also have 'javac' and 'java' added to the classpath. These will be needed to compile and run the code respectively.

# Download

git clone https://github.com/nikhildesai/SimpleDatabase.git

cd SimpleDatabase


# Compile

mkdir target

javac src/com/nikhildesai/db*.java -d target


# Run in console mode

cd target

java com/nikhildesai/db/SimpleDatabase


# Supply a file as input to the program

java com/nikhildesai/db/SimpleDatabase < ../test/test1.txt


Note: The test/ folder contains a few files containing test input

