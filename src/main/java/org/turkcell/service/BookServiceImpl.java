package org.turkcell.service;

import org.turkcell.entities.Employee;
import org.turkcell.entities.Member;
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
    private final List<Transaction> transactions = new ArrayList<Transaction>();
    TransactionRepository transactionRepository;

    public BookServiceImpl(Employee employee, BaseLogger logger, TransactionRepository transactionRepository) {
        this.logger = logger;
        this.employee = employee;
        this.transactionRepository = transactionRepository;
    }


    @Override
    public void addBook(Member member, Book book) {
        logger.logMessage("ID: " + member.getId() + " Member initiated book rental process" + "\n");

        logger.logMessage("Book rental request from member ID: " + member.getId() + " handled by employee ID: " + employee.getId() + "\n");

        //define transaction default values
        Random rand = new Random();
        String operation = "Book Register";
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(5);

        //make transaction with entity member and book
        this.transaction = new Transaction(
                rand.nextInt(50) * book.getId(),
                member,
                employee,
                book,
                startDate,
                endDate,
                operation
        );

        //add transaction to the employee performed task list in the employee entity -list<transactions>
        employee.getTransactions().add(this.transaction);

        //add transaction to the local private storage in the service
        this.transactions.add(this.transaction);

        //update book availability status
        book.setStatus(false);
        //add book to the member's personal library, -list in the member entity -list<books>
        member.getBooks().add(book);

        //transfer transaction to the repository layer and save transaction to the database
        transactionRepository.registerBookBorrowing(this.transaction);
        logger.logMessage("Database registration completed");
    }

    @Override
    public void updateBook(Member member, Book book) {
        logger.logMessage("ID: " + member.getId() + " Member initiated book return process" + "\n");


        //find the related transaction in the local private transactions list
        //if it exists, update service transaction
        boolean exists = false;
        for (Transaction t : this.transactions) {
            if (book.getId() == t.getBook().getId()) {
                exists = true;
                this.transaction = t;
                break;
            }
        }
        //if not, update service transaction to null
        if (!exists) {
            this.transaction = null;
            //if transaction couldn't find return error log message and exit
            logger.logMessage("Book return request from ID: " + member.getId() + " denied by system, there is no record in the database" + "\n");
        } else {
            logger.logMessage("Book return request from ID: " + member.getId() + " supervised by employee ID: " + this.transaction.getEmployee().getId() + "\n");
            int debt = 0;

            //get return time
            LocalDate returnTime = LocalDate.now();

            //calculate time pass between start and return time
            long days = ChronoUnit.DAYS.between(this.transaction.getEndDate(), returnTime);

            //if member return the book late issue a penalty
            //if not accept the return and inform member via log message
            if (days > 0) {
                debt = 5;
                logger.logMessage(debt + " Penalty issued to the member ID: " + member.getId() + " for returning book " + days + " days late" + "\n");
            } else {
                logger.logMessage("Book return request from member ID: " + member.getId() + " supervised by employee ID: " + this.transaction.getEmployee().getId() + "\n");
            }

            //define a new transaction fields
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

            //update book availability to true
            book.setStatus(true);

            //remove the book from member library
            member.getBooks().remove(book);

            //register member debt
            member.setDebt(debt);

            //add transaction to the employee task list
            employee.getTransactions().add(newTransaction);

            //add transaction to the local service storage transactions
            this.transactions.add(newTransaction);

            //transfer the transaction to the repository layer, and save to the database
            transactionRepository.registerBookReturning(newTransaction);

            //inform database save
            logger.logMessage("Database registration completed");
        }


    }
}
