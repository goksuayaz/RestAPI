package com.restapi.api.dao;

import com.restapi.api.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatergoryRepo extends JpaRepository<Category, Long> {


}
