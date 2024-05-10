package com.restapi.api.business.concretes;

import com.restapi.api.business.abs.IAuthorService;
import com.restapi.api.core.exception.NotFoundException;
import com.restapi.api.core.utilies.Msg;
import com.restapi.api.dao.AuthorRepo;
import com.restapi.api.entities.Author;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AuthorManager implements IAuthorService {
    private AuthorRepo authorRepo;

    public AuthorManager(AuthorRepo authorRepo) {
        this.authorRepo = authorRepo;
    }

    @Override
    public Author save(Author author) {
        return this.authorRepo.save(author);
    }

    @Override
    public Author getById(Long id) {
        return this.authorRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public Author update(Author author) {
        this.getById(author.getId());
        return this.authorRepo.save(author);
    }


    @Override
    public String delete(Long id) {
        Author author = this.getById(id);
        this.authorRepo.delete(author);
        return true;
    }

    @Override
    public Page<Author> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return this.authorRepo.findAll(pageable);

    }
}
