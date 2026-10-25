package com.beautyshop.crud.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String entity, String id) {
        super(entity + " introuvable avec l'id " + id);
    }
}
