package org.javaacademy.afisha.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class AfishaControllerAdvice {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleInternalException(RuntimeException e) {
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                .body("Ошибка при выполнение запроса!");
    }
}
