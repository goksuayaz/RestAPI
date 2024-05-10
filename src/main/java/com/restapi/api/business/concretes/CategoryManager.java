package com.restapi.api.business.concretes;

import com.restapi.api.business.abs.ICategoryService;
import com.restapi.api.core.exception.NotFoundException;
import com.restapi.api.core.utilies.Msg;
import com.restapi.api.dao.BookRepo;
import com.restapi.api.dao.CatergoryRepo;
import com.restapi.api.entities.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoryManager implements ICategoryService {

    private CatergoryRepo catergoryRepo;
    private BookRepo bookRepo;

    public CategoryManager(CatergoryRepo catergoryRepo, BookRepo bookRepo) {
        this.catergoryRepo = catergoryRepo;
        this.bookRepo = bookRepo;
    }

    @Override
    public Category getById(Long id) {
        return this.catergoryRepo.findById(id).orElseThrow()) -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public Category save(Category category) {
        return this.catergoryRepo.save(category);
    }

    @Override
    public Category update(Category category) {
        this.getById(category.getId());
        return this.catergoryRepo.save(category);
    }

    @Override
    public String delete(Long id) {

        //bak
    }

    @Override
    public Page<Category> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return this.catergoryRepo.findAll(pageable);
    }
}
