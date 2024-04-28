package com.Assignment.TicketingSystem.ExceptionHandlers;

public class UserNotFoundException extends  Exception{

    public UserNotFoundException(String errorDescription){
        super(errorDescription);

    }

}
