package com.restapi.api.business.concretes;

import com.restapi.api.business.abs.IBookService;
import com.restapi.api.core.exception.NotFoundException;
import com.restapi.api.core.utilies.Msg;
import com.restapi.api.dao.BookRepo;
import com.restapi.api.entities.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BookManager implements IBookService {

    private BookRepo bookRepo;

    public BookManager(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }



    @Override
    public Book getById(Long id) {
        return this.bookRepo.findById(id).orElseThrow()) -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public Book save(Book book) {
        return this.bookRepo.save(book);
    }

    @Override
    public Book update(Book book) {
        this.getById(book.getId());
        return this.bookRepo.save(book);
    }

    @Override
    public String delete(Long id) {
        Book book = this.getById(id);
        this.bookRepo.delete(book);
        return true;
    }

    @Override
    public Page<Book> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return this.bookRepo.findAll(pageable);

    }


}
