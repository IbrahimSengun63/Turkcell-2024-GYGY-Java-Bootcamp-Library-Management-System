package org.turkcell.service;

import org.turkcell.Employee;
import org.turkcell.Member;
import org.turkcell.business.BookController;
import org.turkcell.entities.Book;
import org.turkcell.entities.Transaction;
import org.turkcell.logger.BaseLogger;
import org.turkcell.repository.TransactionRepository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BookServiceImpl implements BookService {

    BaseLogger logger;
    private Transaction transaction;
    Employee employee;
    private List<Transaction> transactions = new ArrayList<Transaction>();
    TransactionRepository transactionRepository;

    public BookServiceImpl(Employee employee,BaseLogger logger, TransactionRepository transactionRepository) {
        this.logger = logger;
        this.employee = employee;
        this.transactionRepository = transactionRepository;
    }


    @Override
    public void addBook(Member member, Book book) {
        logger.logMessage("Member requesting a book borrowing");

        logger.logMessage("Book borrow request from " + member.getName() + " handled by " + employee.getName());
        Random rand = new Random();
        String operation = "Book Register";
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(5);
        this.transaction = new Transaction(
                rand.nextInt(50) * book.getId(),
                member,
                employee,
                book,
                startDate,
                endDate,
                operation
        );
        employee.getTransactions().add(this.transaction);
        this.transactions.add(this.transaction);
        book.setStatus(false);
        member.getBooks().add(book);
        transactionRepository.registerBookBorrowing(this.transaction);
        logger.logMessage("Database registration completed");
    }

    @Override
    public void updateBook(Member member, Book book) {
        logger.logMessage("Member request a book returning");


        //find the transaction
        for (Transaction t : this.transactions) {
            if (book.getId() == t.getBook().getId()) {
                this.transaction = t;
                break;
            } else {
                this.transaction = null;
                break;
            }

        }
        if (this.transaction == null) {
            logger.logMessage("Book return request from " + member.getName() + " denied by system, there is no registration on the database");
        } else {
            logger.logMessage("Book return request from " + member.getName() + " handled by " + this.transaction.getEmployee());
            int debt = 0;
            LocalDate returnTime = LocalDate.now();
            long days = ChronoUnit.DAYS.between(this.transaction.getEndDate(), returnTime);

            //late
            if (days < 0) {
                debt = 5;
                logger.logMessage(debt + " Penalty issued to the member " + this.transaction.getMember() + "for returning book " + days + "late");
            } else {
                logger.logMessage("Book return request from " + member.getName() + " handled by and approved by" + this.transaction.getEmployee());
            }

            String operation = "Book Return";
            Random rand = new Random();

            Transaction newTransaction = new Transaction(
                    rand.nextInt(50) * book.getId(),
                    member,
                    this.transaction.getEmployee(),
                    book,
                    this.transaction.getStartDate(),
                    returnTime,
                    operation
            );

            book.setStatus(true);
            member.getBooks().remove(book);
            member.setDebt(debt);
            employee.getTransactions().add(newTransaction);
            this.transactions.add(newTransaction);
            transactionRepository.registerBookReturning(newTransaction);
            logger.logMessage("Database registration completed");
        }


    }
}
