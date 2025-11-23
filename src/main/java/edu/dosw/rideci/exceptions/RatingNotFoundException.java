package edu.dosw.rideci.exceptions;

public class RatingNotFoundException extends RuntimeException {
    public RatingNotFoundException(String message){
        super(message);
    }
    
}
