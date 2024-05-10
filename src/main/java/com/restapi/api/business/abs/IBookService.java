package com.restapi.api.business.abs;

import com.restapi.api.entities.Book;
import org.springframework.data.domain.Page;

public interface IBookService {

    Book getById(Long id);

    Book save(Book book);

    Book update(Book book);

    String delete(Long id);

    Page<Book> cursor(int page, int pageSize);




}
