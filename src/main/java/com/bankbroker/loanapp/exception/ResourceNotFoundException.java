package com.bankbroker.loanapp.exception;

import lombok.Getter;

@Getter
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String entity, String field, Object value) {
        super(entity + " not found for " + field + " : " + value);
    }
}
