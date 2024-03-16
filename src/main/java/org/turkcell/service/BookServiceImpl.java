package org.turkcell.service;

import org.turkcell.Member;
import org.turkcell.business.BookController;
import org.turkcell.entities.Book;
import org.turkcell.logger.BaseLogger;
import org.turkcell.repository.TransactionRepository;

public class BookServiceImpl implements BookService {

    BaseLogger logger;
    TransactionRepository transactionRepository;

    public BookServiceImpl(BaseLogger logger, TransactionRepository transactionRepository) {
        this.logger = logger;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public void addBook(Member member, Book book) {

        logger.logMessage("Member requesting a book borrowing");
        //business logic
        if(!book.getStatus()) {
            logger.logMessage("Requested book is not available");
        } else {
            transactionRepository.registerBookBorrowing(member, book);
        }

    }

    @Override
    public void updateBook(Member member, Book book) {
        logger.logMessage("Member request a book returning");
        if (book.getStatus()) {
            logger.logMessage("Member doesn't hold the book, book already returned");
        } else {
            transactionRepository.registerBookReturning(member, book);
        }

    }
}
