package com.restapi.api.dto.request.book;


import com.restapi.api.entities.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookSaveRequest {

    @NotNull
    private String name;

    private int publicationYear;
    private int stock;
    private Long publisherId;
    private Long authorId;
    private List<Category> categoryList;
}



