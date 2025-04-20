package com.example.demo.exception;

import com.example.demo.common.MessageConstant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FolderNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleFolderErrorException(FolderNotFoundException folderNotFoundException,
                                                                   WebRequest webRequest){

        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                folderNotFoundException.getMessage(),
                webRequest.getDescription(false),
                MessageConstant.FOLDER_NOT_FOUND
        );

        return new ResponseEntity<>(errorDetails, HttpStatus.OK);
    }


}
