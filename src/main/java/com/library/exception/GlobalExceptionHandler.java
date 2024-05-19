package com.library.exception;

import com.library.dto.ErrorDetailsDto;
import com.library.dto.ErrorDto;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        List<ErrorDetailsDto> detailsDtos = new ArrayList<>();

        e.getFieldErrors().forEach(fieldError -> {
            String field =  fieldError.getField();
            String message = fieldError.getDefaultMessage();
            ErrorDetailsDto errorDetailsDto = ErrorDetailsDto.builder().message(message).field(field).build();
            detailsDtos.add(errorDetailsDto);
        });
        return ErrorDto.builder().errorMessages(detailsDtos).build();
    }


    @ExceptionHandler(AlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorDto handleAlreadyExistException(AlreadyExistException e){
        return ErrorDto.builder().message(e.getMessage()).build();
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto handleNotFoundException(NotFoundException e){
        return ErrorDto.builder().message(e.getMessage()).build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDto handleException(Exception e){
        return ErrorDto.builder().message(e.getMessage()).build();
    }
}
