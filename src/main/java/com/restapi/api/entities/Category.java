package com.restapi.api.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


    @Entity
    @Table(name = "categories")
    @Data
    @AllArgsConstructor
    @NoArgsConstructor

    public class Category {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "category_id", columnDefinition = "serial")
        private Long id;

        @Column(name = "category_name", nullable = false)
        private String name;

        @Column(name = "category_description")
        private String description;

        @ManyToMany(mappedBy = "categoryList", fetch = FetchType.LAZY)
        private List<Book> bookList;

    }
