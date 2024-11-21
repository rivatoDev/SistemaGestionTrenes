package org.example.Excepciones;

public class LowCapacityException extends RuntimeException {
  public LowCapacityException() {
    super();
  }
  public LowCapacityException(String message) {
    super(message);
  }
}