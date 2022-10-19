package model.exception;

public class InvalidNameException extends Exception {

    private String message;

    public InvalidNameException(){
        this.message = "Nom invalide";
    }

    public InvalidNameException(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
