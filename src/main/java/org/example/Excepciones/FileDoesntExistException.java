package org.example.Excepciones;

/**
 * Excepcion hecha para lanzarla cuando se agarra la excepcion FileNotFound.
 */
public class FileDoesntExistException extends RuntimeException {
  public FileDoesntExistException() {
    super();
  }
  public FileDoesntExistException(String message) {
    super(message);
  }
}