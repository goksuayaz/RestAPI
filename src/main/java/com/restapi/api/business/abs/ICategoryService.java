package com.restapi.api.business.abs;

import com.restapi.api.entities.Category;
import org.springframework.data.domain.Page;

public interface ICategoryService {

    Category getById(Long id);

    Category save(Category category);

    Category update(Category category);

    String delete(Long id);

    Page<Category> cursor(int page, int pageSize);
}
