package com.nikhildesai.db;

/**
 * Controller logic to determine the type of command. It uses the
 * DatabaseManagerIF interface to perform the appropriate operation
 * 
 */
public class DatabaseController implements DatabaseControllerIF {
    private DatabaseManagerIF databaseManager;

    public DatabaseController(DatabaseManagerIF databaseManager) {
        this.databaseManager = databaseManager;
    }

    @Override
    public void executeCommand(String inputLine) {
        String[] inputParams = inputLine.split(" ");

        switch (inputParams[0]) {
        case "SET": // SET <name> <value>
            if (inputParams.length != 3) {
                System.out.println("Format of set command is: SET <name> <value>");
                break;
            }
            databaseManager.set(inputParams[1], inputParams[2]);
            break;
        case "GET": // GET <name>
            if (inputParams.length != 2) {
                System.out.println("Format of get command is: SET <name>");
                break;
            }
            System.out.println(databaseManager.get(inputParams[1]));
            break;
        case "UNSET": // UNSET <name>
            if (inputParams.length != 2) {
                System.out.println("Format of set command is: UNSET <name>");
                break;
            }
            databaseManager.unset(inputParams[1]);
            break;
        case "NUMEQUALTO": // NUMEQUALTO <value>
            if (inputParams.length != 2) {
                System.out.println("Format of set command is: NUMEQUALTO <value>");
                break;
            }
            System.out.println(databaseManager.numEqualTo(inputParams[1]));
            break;
        case "END": // END
            System.exit(0);
            break;
        case "BEGIN": // BEGIN
            databaseManager.beginTransaction();
            break;
        case "ROLLBACK": // ROLLBACK
            databaseManager.rollbackCurrentTransaction();
            break;
        case "COMMIT": // COMMIT
            databaseManager.commit();
            break;
        default:
            System.out.println("Invalid command");
        }
    }
}
