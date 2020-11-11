package com.example.but_trucker2.controller.controllerAdvice;

import com.example.but_trucker2.exception.CommentNotFoundException;
import com.example.but_trucker2.exception.ErrorStateHistoryNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class ErrorStateHistoryNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(ErrorStateHistoryNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String commentNotFoundHandler(ErrorStateHistoryNotFoundException ex) {
        return ex.getMessage();
    }
}

