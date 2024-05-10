package com.restapi.api.dto.request.bookBorrowing;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookBorrowingSaveRequest {

    @NotNull
    private String borrowerName;
    //@Email
    private String borrowerEmail;
    private LocalDate borrowingDate;
    private LocalDate returnDate;
    private Long bookId;
}





