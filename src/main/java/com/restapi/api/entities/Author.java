package com.restapi.api.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "authors")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "author_id")
    private Long id;

    @NotNull
    @Column(name = "author_name")
    private String name;

    @Column(name = "author_birth_date")
    private LocalDate birthDate;

    @Column(name = "author_country")
    private String country;

    @OneToMany(mappedBy = "author")
    private List<Book> bookList;


}





