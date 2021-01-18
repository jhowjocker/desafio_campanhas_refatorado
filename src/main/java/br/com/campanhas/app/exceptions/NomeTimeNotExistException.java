package br.com.campanhas.app.exceptions;

public class NomeTimeNotExistException extends RuntimeException {
    public NomeTimeNotExistException(String message) {
        super(message);
    }
}
