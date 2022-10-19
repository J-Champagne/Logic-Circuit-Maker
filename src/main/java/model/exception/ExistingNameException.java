package model.exception;

public class ExistingNameException extends Exception{

    private String message;

    public ExistingNameException(){
        this.message = "Nom existe déjà";
    }

    public ExistingNameException(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
