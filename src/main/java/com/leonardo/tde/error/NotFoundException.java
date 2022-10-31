package com.leonardo.tde.error;

public class NotFoundException extends RuntimeException {

    private String message;

    // Construtores
    // Construtor Com Todos Argumentos
    public NotFoundException(String message) {
        super(message);
        this.message = message;
    }

    // Getters e Setters
    // Message
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
