package com.nikhildesai.db;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple transaction implementation which holds the old state of variables
 * being modified by the transaction. Upon rollback, the old values will be
 * applied to the database. The implementation would work well if the number of
 * variables modified in each transaction is small.
 */
public class TransactionImpl implements TransactionIF {

    private DatabaseIF database;

    // A list containing old state of the variables being modified in the
    // current transaction
    private List<DatabaseEntry> oldState = new ArrayList<DatabaseEntry>();
    private boolean isCommitted = false;
    private boolean isRolledBack = false;

    public TransactionImpl(DatabaseIF database) {
        this.database = database;
    }

    @Override
    public void rollback() {
        if (isFinished()) { // if transaction is already committed, return early
            return;
        }

        for (DatabaseEntry entry : oldState) {
            database.set(entry.getName(), entry.getValue()); // O(N) , N is the
                                                             // number of
                                                             // variables being
                                                             // modified
        }
        isRolledBack = true;
    }

    @Override
    public void commit() {
        isCommitted = true;
        oldState.clear();
    }

    @Override
    public void addState(String variableName, String value) {
        if (isFinished()) {
            return;
        }

        if (!oldState.contains(variableName)) {
            oldState.add(new DatabaseEntry(variableName, database.get(variableName)));
        }
    }

    private boolean isFinished() {
        return isCommitted || isRolledBack;
    }
}
