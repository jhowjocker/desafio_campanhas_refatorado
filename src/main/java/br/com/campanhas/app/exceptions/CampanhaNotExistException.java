package br.com.campanhas.app.exceptions;

public class CampanhaNotExistException extends RuntimeException{
    public CampanhaNotExistException(String message){
        super(message);
    }
}
