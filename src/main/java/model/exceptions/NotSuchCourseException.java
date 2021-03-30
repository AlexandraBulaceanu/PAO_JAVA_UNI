package model.exceptions;

public class NotSuchCourseException extends Exception{
    public NotSuchCourseException(String message) {
        super(message);
    }
}
//TODO - THROW IT WHERE NEEDED