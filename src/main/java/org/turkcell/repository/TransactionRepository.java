package org.turkcell.repository;

import org.turkcell.Member;
import org.turkcell.entities.Book;
import org.turkcell.entities.Transaction;

public interface TransactionRepository {
    void registerBookBorrowing(Transaction transaction);
    void registerBookReturning(Transaction transaction);
}
