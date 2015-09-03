# SimpleDatabase
Simple in-memory database implemented using Java. It has a command line interface and can also accept input via a text file containing a series of commands (one command per line).

Supported commands:

1) SET name value – Set the variable name to the value value. Neither variable names nor values will contain spaces
  
2) GET name – Print out the value of the variable name, or NULL if that variable is not set.

3) UNSET name – Unset the variable name, making it just like that variable was never set.

4) NUMEQUALTO value – Print out the number of variables that are currently set to value. If no variables equal that value, print 0.

5) END – Exit the program.

In addition, the database also supports transactions using the following commands:

6) BEGIN – Open a new transaction block. Transaction blocks can be nested; a BEGIN can be issued inside of an existing block.

7) ROLLBACK – Undo all of the commands issued in the most recent transaction block, and close the block. Print nothing if successful, or print NO TRANSACTION if no transaction is in progress.

8) COMMIT – Close all open transaction blocks, permanently applying the changes made in them. Print nothing if successful, or print NO TRANSACTION if no transaction is in progress.

All command names are case-sensitive

# Pre-requisites

You need to have JDK7+ installed. Also have 'javac' and 'java' added to the classpath. These will be needed to compile and run the code respectively.

# Download

git clone https://github.com/nikhildesai/SimpleDatabase.git

cd SimpleDatabase


# Compile

mkdir target

javac src/com/nikhildesai/db/*.java -d target


# Run in console mode

cd target

java com/nikhildesai/db/SimpleDatabase


# Supply a file as input to the program

java com/nikhildesai/db/SimpleDatabase < ../test/test1.txt


Note: The test/ folder contains a few files containing test input


# Note on Code Design

1) The source code is in the src/com/nikhildesai/db folder. The entry point to the program is the main() function in the SimpleDatabase.java file.

2) The program either accepts input from a file OR runs in console mode accepting one line of input at a time until it receives the 'END' command or the process is terminated

3) The object dependencies are roughly as follows:

SimpleDatabase -> DatabaseController

DatabaseController -> DatabaseManager

DatabaseManager -> Database, TransactionManager

TransactionManager -> Transaction

Transaction -> Database

4) DatabaseImpl uses a TreeMap implementation for storing the data as it's operations are O(logN) where N is the number of entries in the database. It also uses a HashMap to store 'numEqualTo' values in order to achieve O(1)

5) TransactionManager uses a stack to store nested transactions so that they can be rolled back one by one if needed

6) Each transaction stores the original values of the variables being altered. The rollback operation loops through these variables and hence time complexity is O(m) where m is the number of variables stored in the transaction. Since number of variables in a transaction is small (give assumption), the performance will not be that bad
