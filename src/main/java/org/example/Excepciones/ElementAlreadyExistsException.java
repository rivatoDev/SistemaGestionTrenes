package org.example.Excepciones;

public class ElementAlreadyExistsException extends RuntimeException {
    public ElementAlreadyExistsException() {
        super();
    }
    public ElementAlreadyExistsException(String message) {
        super(message);
    }
}