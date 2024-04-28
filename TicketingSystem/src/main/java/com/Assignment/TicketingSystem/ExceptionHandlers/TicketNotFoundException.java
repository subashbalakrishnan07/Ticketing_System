package com.Assignment.TicketingSystem.ExceptionHandlers;

public class TicketNotFoundException extends Exception {
    public TicketNotFoundException(String errorDescription){
        super(errorDescription);

    }
}
