package br.com.lucassoaresdev.codepro.exceptions;

public class MessageBadRequestException extends RuntimeException {
    private String message;

    public MessageBadRequestException(String message){
        this.message = message;
    }

    @Override
    public String getMessage(){
        return message;
    }
}
