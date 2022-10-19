package model.exception;

public class InvalidConnectionException extends Exception{

    private String message;

    public InvalidConnectionException(){
        this.message = "Connexion invalide";
    }

    public InvalidConnectionException(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
