package org.turkcell.repository;

import org.turkcell.entities.Transaction;

public interface TransactionRepository {
    void registerBookBorrowing(Transaction transaction);
    void registerBookReturning(Transaction transaction);
}
