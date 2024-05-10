package com.restapi.api.core.config.modelMapper;



import com.restapi.api.core.exception.NotFoundException;
import com.restapi.api.core.result.Result;
import com.restapi.api.core.result.ResultData;
import com.restapi.api.core.utilies.ResultHelper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

    @ControllerAdvice
    public class GlobalException {

        @ExceptionHandler(NotFoundException.class)
        public ResponseEntity<Result> handleNotFoundException(NotFoundException e) {
            return new ResponseEntity<>(ResultHelper.notFoundError(e.getMessage()), HttpStatus.NOT_FOUND);
        }


        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<ResultData<List<String>>> handleValidationErrors(MethodArgumentNotValidException e) {
            List<String> validationErrorList = e.getBindingResult().getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage).collect(Collectors.toList());

            return new ResponseEntity<>(ResultHelper.validateError(validationErrorList), HttpStatus.BAD_REQUEST);
        }
    }
}