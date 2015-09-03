package com.nikhildesai.db;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Following operations are supported: <br>
 * <br>
 * set(name, value): set a value<br>
 * get(name): get a value<br>
 * unset(name): reset the value <br>
 * numEqualTo(value): find number of objects having the same value<br>
 * <br>
 * 
 * The implementation is not thread-safe. The client needs to take care of
 * concurrent access.
 * 
 * The implementation also caches the numEqualTo value for each value stored.
 * 
 */
public class DatabaseImpl implements DatabaseIF {

    /**
     * TreeMap implementation gives O(logN) for it's operations
     */
    private Map<String, String> database = new TreeMap<String, String>();

    private Map<String, Integer> numEqualToMap = new HashMap<String, Integer>();

    @Override
    public void set(String name, String value) {
        // decrement numEqualTo for old value
        if (numEqualToMap.get(database.get(name)) != null) {
            numEqualToMap.put(database.get(name), numEqualToMap.get(database.get(name)) - 1);
        }

        // update value
        database.put(name, value); // O(logN)

        // set numEqualTo for new value
        int numEqualTo;
        if (numEqualToMap.get(value) == null) { // O(1)
            numEqualTo = 1;
        } else {
            numEqualTo = numEqualToMap.get(value) + 1; // O(1)
        }
        numEqualToMap.put(value, numEqualTo); // O(1)
    }

    @Override
    public String get(String name) {
        return database.get(name); // O(logN)
    }

    @Override
    public void unset(String name) {
        if (database.get(name) != null) {
            numEqualToMap.put(database.get(name), numEqualToMap.get(database.get(name)) - 1); // O(1)
        }
        database.remove(name); // O(logN)
    }

    @Override
    public int numEqualTo(String value) { // O(1) at the expense of keeping an
                                           // additional map 'numEqualToMap'
        if (numEqualToMap.get(value) == null) { // O(1)
            return 0;
        }

        return numEqualToMap.get(value); // O(1)
    }
}
