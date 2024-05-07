package com.Assignment.ticketingsystem.exceptionhandler;

public class TicketNotFoundException extends Exception {
    public TicketNotFoundException(String errorDescription){
        super(errorDescription);

    }
}
