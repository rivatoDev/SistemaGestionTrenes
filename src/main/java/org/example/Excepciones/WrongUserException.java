package org.example.Excepciones;

/**
 * Excepcion que se lanza cuando se utiliza un usuario del tipo incorrecto.
 */
public class WrongUserException extends RuntimeException {
    public WrongUserException() {
        super();
    }
    public WrongUserException(String message) {
        super(message);
    }
}