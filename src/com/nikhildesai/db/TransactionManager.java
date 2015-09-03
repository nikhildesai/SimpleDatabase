package com.nikhildesai.db;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Transaction management implementation that supports nested transactions
 * 
 */
public class TransactionManager implements TransactionManagerIF {

    // Use as a stack to store nested transactions
    private Deque<TransactionIF> transactionStack = new ArrayDeque<TransactionIF>();
    private TransactionIF currentTransaction;

    @Override
    public TransactionIF createTransaction(DatabaseIF database) {
        if (isTransactionInProgress()) {
            transactionStack.push(currentTransaction);
        }
        currentTransaction = new TransactionImpl(database);
        return currentTransaction;
    }

    @Override
    public void rollbackCurrentTransaction() {
        if (isTransactionInProgress()) {
            currentTransaction.rollback();
        } else {
            System.out.println("NO TRANSACTION");
        }

        currentTransaction = transactionStack.pollFirst();
    }

    @Override
    public void commit() {
        if (isTransactionInProgress()) {
            currentTransaction.commit();
            currentTransaction = null;
            transactionStack.clear();
        } else {
            System.out.println("NO TRANSACTION");
        }
    }

    @Override
    public void saveOldState(String name, String value) {
        if (isTransactionInProgress()) {
            currentTransaction.addState(name, value);
        }
    }

    @Override
    public boolean isTransactionInProgress() {
        return currentTransaction != null;
    }
}
