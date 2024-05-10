package com.restapi.api.dao;

import com.restapi.api.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepo extends JpaRepository<Book, Long> {

    boolean existsByCategoryList_Id(Long categoryId);
}
