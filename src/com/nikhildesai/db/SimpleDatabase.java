package com.nikhildesai.db;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Bootstrapping for the database. There are other ways to bootstrap but thought
 * this might the simplest way that does not involve dependencies
 * 
 * Also, has some logic to read input from a file or from the console.
 */
public class SimpleDatabase {

    public static void main(String[] args) throws IOException {
        // bootstrapping
        DatabaseIF database = new DatabaseImpl();
        TransactionManagerIF transactionManager = new TransactionManager();
        DatabaseManagerIF databaseManager = new DatabaseManagerImpl(database, transactionManager);
        DatabaseControllerIF controller = new DatabaseController(databaseManager);

        // Either read from a file OR accept one command at a time
        Scanner scanner = null;
        if (args.length == 1) {
            // Read from file
            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader(new File(args[0])));
            } catch (FileNotFoundException e) {
                System.out.println("File not found! Exiting!");
            }

            String line = null;
            while ((line = br.readLine()) != null) {
                controller.executeCommand(line);
            }

            br.close();
        } else if (args.length == 0) {
            scanner = new Scanner(System.in);
            while (true) {
                String nextLine = scanner.nextLine();
                controller.executeCommand(nextLine);
            }
        } else {
            System.out.println("Invalid command.");
            System.out.println("Usage:");
            System.out.println("SET <name> <value>");
            System.out.println("GET <name>");
            System.out.println("UNSET <name>");
            System.out.println("NUMEQUALTO <value>");
            System.out.println("END");
            System.out.println("BEGIN");
            System.out.println("ROLLBACK");
            System.out.println("COMMIT");
        }

        System.out.println("Good bye!");
    }
}
