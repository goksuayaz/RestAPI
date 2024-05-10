package com.restapi.api.business.abs;

import com.restapi.api.entities.BookBorrowing;
import org.springframework.data.domain.Page;


public interface IBookBorrowingService {


    BookBorrowing getById(Long id);

    BookBorrowing save (BookBorrowing bookBorrowing);

    BookBorrowing update(BookBorrowing bookBorrowing);


    String delete(Long id);

    Page<BookBorrowing> cursor(int page, int pageSize);






}
