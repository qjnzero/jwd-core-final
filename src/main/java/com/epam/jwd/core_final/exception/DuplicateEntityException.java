package com.epam.jwd.core_final.exception;

public class DuplicateEntityException extends Exception {

    public DuplicateEntityException(String entityName) {
        super(entityName);
    }
}
