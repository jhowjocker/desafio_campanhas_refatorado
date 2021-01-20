package br.com.campanhas.app.exceptions;

public class AssociadoNotExistException extends RuntimeException{
    public AssociadoNotExistException(String message){
        super(message);
    }
}
