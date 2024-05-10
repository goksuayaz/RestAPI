package com.restapi.api.business.concretes;

import com.restapi.api.business.abs.IBookBorrowingService;
import com.restapi.api.core.exception.NotFoundException;
import com.restapi.api.dao.BookBorrowingRepo;
import com.restapi.api.dao.BookRepo;
import com.restapi.api.entities.Book;
import com.restapi.api.entities.BookBorrowing;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BookBorrowingManager implements IBookBorrowingService {

    private BookBorrowingRepo bookBorrowingRepo;
    private BookRepo bookRepo;


    public BookBorrowingManager(BookBorrowingRepo bookBorrowingRepo, BookRepo bookRepo) {
        this.bookBorrowingRepo = bookBorrowingRepo;
        this.bookRepo = bookRepo;
    }

    @Override
    public BookBorrowing getById(Long id) {
        return this.bookBorrowingRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND))
    }

    @Override
    public BookBorrowing save(BookBorrowing bookBorrowing) {
        Book book = bookRepo.findById(bookBorrowing.getBook().getId())
                .orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
        if (book.getStock() > 0){
            book.setStock(book.getStock() - 1);
            bookRepo.save(book);
            bookBorrowing.setBook(book);
            return this.bookBorrowingRepo.save(bookBorrowing);
        }
        else {
            throw new NotFoundException(Msg.STOCK_NOT_ENOUGH);
        }
    }


    @Override
    public BookBorrowing update(BookBorrowing bookBorrowing) {
        this.getById(bookBorrowing.getId());
        return this.bookBorrowingRepo.save(bookBorrowing);
    }

    @Override
    public String delete(Long id) {
        BookBorrowing bookBorrowing = this.get(id);
        this.bookBorrowingRepo.delete(bookBorrowing);
        return true;
    }

    @Override
    public Page<BookBorrowing> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return this.bookBorrowingRepo.findAll(pageable);
    }
}
