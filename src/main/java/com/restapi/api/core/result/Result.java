package com.restapi.api.core.result;

import lombok.Getter;

@Getter
public class Result {
    private String message;
    private String code;
    private boolean status;

    public Result(String message, String code, boolean status) {
        this.message = message;
        this.code = code;
        this.status = status;
    }
}


