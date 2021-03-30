package model.exceptions;

public class NoSuchUserException extends Exception{
    public NoSuchUserException(String message) {
        super(message);
    }
}
//TODO - THROW IT WHERE NEEDED