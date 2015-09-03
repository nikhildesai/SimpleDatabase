package com.nikhildesai.db;

public interface DatabaseIF {

    void set(String name, String value);

    String get(String name);

    void unset(String name);

    int numEqualTo(String value);

}
