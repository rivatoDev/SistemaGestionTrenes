package org.example.Excepciones;

public class JSONEmptyFileException extends RuntimeException {
    public JSONEmptyFileException() {
        super();
    }
    public JSONEmptyFileException(String message) {
        super(message);
    }
}