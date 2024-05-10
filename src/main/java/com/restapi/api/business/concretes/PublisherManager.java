package com.restapi.api.business.concretes;

import com.restapi.api.business.abs.IPublisherService;
import com.restapi.api.core.exception.NotFoundException;
import com.restapi.api.core.utilies.Msg;
import com.restapi.api.dao.PublisherRepo;
import com.restapi.api.entities.Publisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PublisherManager implements IPublisherService {

    private PublisherRepo publisherRepo;

    public PublisherManager(PublisherRepo publisherRepo) {
        this.publisherRepo = publisherRepo;
    }

    @Override
    public Publisher getById(Long id) {
        return this.publisherRepo.findById(id).orElseThrow()) -> new NotFoundException(Msg.NOT_FOUND));

    }
    @Override
    public Publisher save(Publisher publisher) {
        return this.publisherRepo.save(publisher);
    }

    @Override
    public Publisher update(Publisher publisher) {
        this.getById(publisher.getId());
        return this.publisherRepo.save(publisher);
    }

    @Override
    public String delete(Long id) {
        Publisher publisher = this.getById(id);
       this.publisherRepo.delete(publisher);
       return true;
    }

    @Override
    public Page<Publisher> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        this.publisherRepo.findAll(pageable);
    }

}

