package com.restapi.api.dto.request.publisher;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PublisherSaveRequest {
    @NotNull
    private String name;
    private int establishmentYear;
    private String address;

}





