package com.restapi.api.dto.request.publisher;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PublisherUpdateRequest {


    private Long id;
    @NotNull
    private String name;
    private int establishmentYear;
    private String address;

}



