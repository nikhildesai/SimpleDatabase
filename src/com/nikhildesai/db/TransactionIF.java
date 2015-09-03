package com.nikhildesai.db;

public interface TransactionIF {

    void rollback();

    void commit();

    void addState(String variableName, String value);
}
