package com.sohail.DepartmentManagement.exception;

public class DuplicateResourceFoundException extends RuntimeException{
    private String exception;

    public DuplicateResourceFoundException(String exception , String message){
        super(message);
        this.exception = exception;
    }
}
