package com.restapi.api.dto.request.category;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CategoryUpdateRequest {


    private Long id;

    @NotNull
    private String name;

    private String description;
}



