package br.com.campanhas.app.exceptions;

public class ClubeDuplicadoException extends RuntimeException{
    public ClubeDuplicadoException(String message){
        super(message);
    }
}
