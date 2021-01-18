package br.com.campanhas.app.exceptions;

public class CampanhaDuplicadaException extends RuntimeException {
    public CampanhaDuplicadaException(String message) {
        super(message);
    }
}
