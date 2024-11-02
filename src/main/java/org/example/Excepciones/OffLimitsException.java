package org.example.Excepciones;

/**
 * Se lanza cuando se sobrepasa el limit de algo.
 */
public class OffLimitsException extends RuntimeException {
    public OffLimitsException() {
        super();
    }
    public OffLimitsException(String message) {
        super(message);
    }
}