package model.exception;

public class InvalidCircuitException extends Exception {

    private String message;

    public InvalidCircuitException(){
        this.message = "Circuit Invalide";
    }

    public InvalidCircuitException(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
