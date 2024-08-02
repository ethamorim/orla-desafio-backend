package com.ethamorim.orlachallengebackend;

import com.ethamorim.orlachallengebackend.exception.NoRecordFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class OrlaChallengeBackendExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NoRecordFoundException.class)
    public ResponseEntity<Map<String, String>> handleBadRequest(NoRecordFoundException e) {
        Map<String, String> message = new HashMap<>();
        message.put("error", e.getMessage());
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }
}
