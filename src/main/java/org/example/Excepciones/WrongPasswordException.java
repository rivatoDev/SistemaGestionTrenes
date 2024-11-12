package org.example.Excepciones;

/**
 * Se utiliza para cuando la clave introducida es incorrecta.
 */
public class WrongPasswordException extends RuntimeException {
    public WrongPasswordException() {
        super();
    }
    public WrongPasswordException(String message) {
        super(message);
    }
}
