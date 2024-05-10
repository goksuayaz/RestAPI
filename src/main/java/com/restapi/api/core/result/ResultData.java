package com.restapi.api.core.result;

import lombok.Getter;

@Getter
public class ResultData <T> extends Result {

    private T data;


    public ResultData(String message, String code, boolean status) {
        super(message, code, status);

        this.data = data;
    }
}





