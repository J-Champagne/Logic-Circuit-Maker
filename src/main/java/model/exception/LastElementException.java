package model.exception;

public class LastElementException extends Exception{

    private String message;

    public LastElementException(){
        this.message = "Dernier élément";
    }

    public LastElementException(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
