package org.turkcell.repository;

import org.turkcell.entities.Transaction;

public interface TransactionRepository {
    void saveBookRental(Transaction transaction);
    void updateBookRental(Transaction transaction);
}
