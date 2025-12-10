package com.bankbroker.loanapp.exception;

import lombok.Getter;

@Getter
//public class ResourceNotFoundException extends RuntimeException {
//    private final String resourceName;
//    private final String fieldName;
//    private final Object fieldValue;
//
//    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
//        super(String.format("%s not found for %s : %s", resourceName, fieldName, fieldValue));
//        this.resourceName = resourceName;
//        this.fieldName = fieldName;
//        this.fieldValue = fieldValue;
//    }
//}


public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String entity, String field, Object value) {
        super(entity + " not found for " + field + " : " + value);
    }
}
