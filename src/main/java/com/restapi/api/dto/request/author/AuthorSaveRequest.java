package com.restapi.api.dto.request.author;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorSaveRequest {

    @NotNull
    private String name;
    private LocalDate birthDate;
    private String country;


}
