package com.restapi.api.business.abs;


import com.restapi.api.entities.Publisher;
import org.springframework.data.domain.Page;

public interface IPublisherService {

    Publisher getById(Long id);

    Publisher save(Publisher publisher);

    Publisher update(Publisher publisher);

    String delete(Long id);

    Page<Publisher> cursor(int page, int pageSize);
}
