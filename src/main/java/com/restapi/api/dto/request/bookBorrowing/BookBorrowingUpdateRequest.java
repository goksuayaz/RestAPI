package com.restapi.api.dto.request.bookBorrowing;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class BookBorrowingUpdateRequest {


    private Long id;

    @NotNull
    private String borrowerName;

    private LocalDate borrowingDate;
    private LocalDate returnDate;
}

