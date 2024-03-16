package org.turkcell.repository;

import org.turkcell.Employee;
import org.turkcell.Member;
import org.turkcell.entities.Book;
import org.turkcell.entities.Transaction;
import org.turkcell.logger.BaseLogger;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Random;

//logger.logMessage(book.getName() + " named book successfully lent to the member " + member.getName() + "by employee" + employee.getName() + "lending duration end in ");
//logger.logMessage(book.getName() + " named book successfully received from the member " + member.getName() + "by employee" + transaction.getEmployee());
public class TransactionRepositoryImpl implements TransactionRepository {
    BaseLogger logger;

    public TransactionRepositoryImpl(BaseLogger logger) {
        this.logger = logger;
    }

    @Override
    public void registerBookBorrowing(Transaction transaction) {
        logger.logMessage(transaction.getBook().getName() + " named book successfully lent to the member " + transaction.getMember().getName() + "by employee" + transaction.getEmployee().getName() + "lending duration end in " + transaction.getEndDate());
    }

    @Override
    public void registerBookReturning(Transaction transaction) {
        logger.logMessage(transaction.getBook().getName() + " named book successfully received from the member " + transaction.getMember().getName() + "by employee" + transaction.getEmployee().getName());
    }
}
