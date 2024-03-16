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

public class TransactionRepositoryImpl implements TransactionRepository {
    BaseLogger logger;
    Transaction transaction;
    Employee employee;
    private List<Transaction> transactions;


    @Override
    public void registerBookBorrowing(Member member, Book book) {
        logger.logMessage("Book borrow request from " + member.getName() + " handled by " + employee.getName());
        Random rand = new Random();
        String operation = "Book Register";
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(5);
        transaction = new Transaction(
                rand.nextInt(50) * book.getId(),
                member,
                employee,
                book,
                startDate,
                endDate,
                operation
        );
        employee.getTransactions().add(transaction);
        transactions.add(transaction);
        book.setStatus(false);
        member.getBooks().add(book);
        logger.logMessage(book.getName() + " named book successfully lent to the member " + member.getName() + "by employee" + employee.getName() + "lending duration end in " + endDate);


    }

    @Override
    public void registerBookReturning(Member member, Book book) {

        //find the transaction
        for (Transaction t : transactions) {
            if (book.getName() == t.getBook().getName()) {
                transaction = t;
                break;
            } else {
                transaction = null;
                break;
            }

        }
        if (transaction == null) {
            logger.logMessage("Book return request from " + member.getName() + " denied by system, there is no registration on the database");
        } else {
            logger.logMessage("Book return request from " + member.getName() + " handled by " + transaction.getEmployee());
            int debt = 0;
            LocalDate returnTime = LocalDate.now();
            long days = ChronoUnit.DAYS.between(transaction.getEndDate(), returnTime);

            //late
            if (days < 0) {
                debt = 5;
                logger.logMessage(debt + " Penalty issued to the member " + transaction.getMember() + "for returning book " + days + "late");
            } else {
                logger.logMessage("Book return request from " + member.getName() + " handled by and approved by" + transaction.getEmployee());
            }

            String operation = "Book Return";
            Random rand = new Random();

            Transaction newTransaction = new Transaction(
                    rand.nextInt(50) * book.getId(),
                    member,
                    transaction.getEmployee(),
                    book,
                    transaction.getStartDate(),
                    returnTime,
                    operation
            );

            book.setStatus(true);
            member.getBooks().remove(book);
            member.setDebt(debt);
            employee.getTransactions().add(newTransaction);
            transactions.add(newTransaction);
            logger.logMessage(book.getName() + " named book successfully received from the member " + member.getName() + "by employee" + transaction.getEmployee());
        }


    }
}
