package com.nikhildesai.db;

/**
 * Implementation of a database that supports transactions
 * 
 */
public class DatabaseManagerImpl implements DatabaseManagerIF {

    private DatabaseIF database;
    private TransactionManagerIF transactionManager;

    public DatabaseManagerImpl(DatabaseIF database, TransactionManagerIF transactionManager) {
        this.database = database;
        this.transactionManager = transactionManager;
    }

    @Override
    public void set(String name, String value) {
        if (transactionManager.isTransactionInProgress()) {
            transactionManager.saveOldState(name, database.get(name));
        }
        database.set(name, value);
    }

    @Override
    public void unset(String name) {
        if (transactionManager.isTransactionInProgress()) {
            transactionManager.saveOldState(name, database.get(name));
        }
        database.unset(name);
    }

    @Override
    public String get(String name) {
        return database.get(name);
    }

    @Override
    public Integer numEqualTo(String value) {
        return database.numEqualTo(value);

    }

    @Override
    public void beginTransaction() {
        transactionManager.createTransaction(database);
    }

    @Override
    public void commit() {
        transactionManager.commit();
    }

    @Override
    public void rollbackCurrentTransaction() {
        transactionManager.rollbackCurrentTransaction();
    }
}
