package com.Assignment.ticketingsystem.exceptionhandler;

public class UserNotFoundException extends  Exception{

    public UserNotFoundException(String errorDescription){
        super(errorDescription);

    }

}
