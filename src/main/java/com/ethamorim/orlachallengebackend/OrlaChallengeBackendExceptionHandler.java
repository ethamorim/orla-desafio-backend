package com.ethamorim.orlachallengebackend;

import com.ethamorim.orlachallengebackend.exception.NoRecordFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * Responsável pelo direcionamento de exceções para o tratamento adequado,
 * buscando padronizar os erros lançados.
 *
 * @author ethamorim
 */
@ControllerAdvice
public class OrlaChallengeBackendExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoRecordFoundException.class)
    public ResponseEntity<Map<String, String>> handleRecordNotFound(NoRecordFoundException e) {
        Map<String, String> message = new HashMap<>();
        message.put("error", e.getMessage());
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, String>> handleDuplicateRecord() {
        Map<String, String> message = new HashMap<>();
        message.put("error", "There is already a record with the given values");
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }
}
