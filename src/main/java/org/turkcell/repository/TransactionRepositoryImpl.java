package org.turkcell.repository;

import org.turkcell.entities.Transaction;

public class TransactionRepositoryImpl implements TransactionRepository {

    @Override
    public void saveBookRental(Transaction transaction) {

        System.out.println((transaction.getBook().getId() + " ID book successfully lent to the member ID " + transaction.getMember().getId() + " by employee ID " + transaction.getEmployee().getId() + " lending duration end in " + transaction.getEndDate() + "\n"));
    }

    @Override
    public void updateBookRental(Transaction transaction) {
        System.out.println(transaction.getBook().getId() + " ID book successfully received from the member ID " + transaction.getMember().getId() + " by employee ID " + transaction.getEmployee().getId() + "\n");
    }
}
