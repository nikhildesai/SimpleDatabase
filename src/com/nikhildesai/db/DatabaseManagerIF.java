package com.nikhildesai.db;

public interface DatabaseManagerIF {

    void set(String name, String value);

    void unset(String name);

    String get(String name);

    Integer numEqualTo(String value);

    void beginTransaction();

    void commit();

    void rollbackCurrentTransaction();

}