package org.example.Excepciones;

public class HeightOffLimitsException extends RuntimeException {
    public HeightOffLimitsException(String message) {
        super(message);
    }
}