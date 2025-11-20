package edu.dosw.rideci.exceptions;

public class ProfileNotFoundException extends RuntimeException {
    public ProfileNotFoundException(String message){
        super(message);
    }
    
}
