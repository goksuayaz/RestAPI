package com.restapi.api.business.abs;

import com.restapi.api.entities.Author;
import org.springframework.data.domain.Page;

public interface IAuthorService {

    Author save(Author author);

    Author getById(Long id);

    Author update(Author author);

    String delete(Long id);

    Page<Author> cursor(int page, int pageSize);
}
