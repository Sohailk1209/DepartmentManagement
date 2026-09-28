package com.sohail.DepartmentManagement.exception;

public class ResourceNotFoundException extends RuntimeException{

    private String exception;

    public ResourceNotFoundException(String exception , String message){
        super(message);
        this.exception = exception;
    }
}
