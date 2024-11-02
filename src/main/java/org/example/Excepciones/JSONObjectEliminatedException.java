package org.example.Excepciones;

/**
 * Excepcion que se lanza cuando se intenta utilizar un JSON de un archivo el cual esta eliminado.
 */
public class JSONObjectEliminatedException extends RuntimeException {
    public JSONObjectEliminatedException(String message) {
        super(message);
    }
}