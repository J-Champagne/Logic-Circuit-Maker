package model.exception;

public class MaxElementsException extends Exception{

    private String message;

    public MaxElementsException(){
        this.message = "Maximum atteint";
    }

    public MaxElementsException(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
