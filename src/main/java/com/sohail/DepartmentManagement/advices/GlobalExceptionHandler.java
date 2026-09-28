package com.sohail.DepartmentManagement.advices;

import com.sohail.DepartmentManagement.exception.DuplicateResourceFoundException;
import com.sohail.DepartmentManagement.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        Error error = Error.builder()
                .timeStamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST)
                .subErrors(
                        exception.getBindingResult()
                                .getAllErrors()
                                .stream()
                                .map(objectError -> objectError.getDefaultMessage())
                                .toList()
                )
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(buildResponse(error));
    }

    @ExceptionHandler(DuplicateResourceFoundException.class)
    public ResponseEntity<Response> handleDuplicateResourceFoundException(DuplicateResourceFoundException exception){
        Error error = Error.builder()
                .status(HttpStatus.CONFLICT)
                .timeStamp(LocalDateTime.now())
                .subErrors(List.of(exception.getMessage()))
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(buildResponse(error));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Response> handleResourceNotFoundException(ResourceNotFoundException exception){
        Error error = Error.builder()
                .status(HttpStatus.NOT_FOUND)
                .timeStamp(LocalDateTime.now())
                .subErrors(List.of(exception.getMessage()))
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(buildResponse(error));
    }

    private Response buildResponse(Error error){
        return Response.builder()
                .error(error)
                .timeStamp(LocalDateTime.now())
                .build();
    }
}