package com.nikhildesai.db;

public interface TransactionManagerIF {
    
    TransactionIF createTransaction(DatabaseIF database);
    
    void rollbackCurrentTransaction();

    void commit();

    void saveOldState(String name, String value);

    boolean isTransactionInProgress();

}
