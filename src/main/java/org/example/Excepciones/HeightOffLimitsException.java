package org.example.Excepciones;

public class HeightOffLimitsException extends RuntimeException {
    public HeightOffLimitsException() {
        super();
    }
    public HeightOffLimitsException(String message) {
        super(message);
    }
}