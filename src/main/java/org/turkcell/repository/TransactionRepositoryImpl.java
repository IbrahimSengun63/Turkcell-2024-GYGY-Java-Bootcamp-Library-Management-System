package org.turkcell.repository;
import org.turkcell.entities.Transaction;

public class TransactionRepositoryImpl implements TransactionRepository {

    @Override
    public void registerBookBorrowing(Transaction transaction) {

        System.out.println((transaction.getBook().getName() + " named book successfully lent to the member " + transaction.getMember().getName() + "by employee" + transaction.getEmployee().getName() + "lending duration end in " + transaction.getEndDate()));
    }

    @Override
    public void registerBookReturning(Transaction transaction) {
        System.out.println(transaction.getBook().getName() + " named book successfully received from the member " + transaction.getMember().getName() + "by employee" + transaction.getEmployee().getName());
    }
}
