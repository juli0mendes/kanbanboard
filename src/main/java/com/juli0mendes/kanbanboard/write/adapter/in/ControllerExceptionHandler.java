package com.juli0mendes.kanbanboard.write.adapter.in;

import com.juli0mendes.kanbanboard.write.domain.exception.DuplicatedDataException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> onMethodArgumetNotValidadException(MethodArgumentNotValidException exception) {

        Map<String, String> mapErrors = exception
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));

        return new ResponseEntity<>(mapErrors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicatedDataException.class)
    public ResponseEntity<Object> onDuplicatedDataException(DuplicatedDataException exception) {
        return new ResponseEntity<>(exception.getErrors(), HttpStatus.BAD_REQUEST);
    }
}
