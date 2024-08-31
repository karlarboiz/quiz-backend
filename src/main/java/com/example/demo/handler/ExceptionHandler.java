package com.example.demo.handler;

import com.example.demo.model.dto.ErrorBody;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.awt.*;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception,
            HttpHeaders httpHeaders, HttpStatusCode httpStatusCode,
            WebRequest webRequest){

        Map<String, Object> objectBody = new LinkedHashMap<>();
        objectBody.put("Current Timestamp", new Date());
        objectBody.put("Status", 200);

        // Get all errors
        List<ErrorBody> exceptionalErrors
                = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map((x) -> {
                    ErrorBody errorBody = new ErrorBody();

                    errorBody.setField(x.getField());
                    errorBody.setMessage(x.getDefaultMessage());
                    return errorBody;
                })
                .collect(Collectors.toList());
        objectBody.put("Errors", exceptionalErrors);

        return new ResponseEntity<>(objectBody, httpStatusCode);
    }
}
