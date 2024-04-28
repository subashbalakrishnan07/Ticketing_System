package com.Assignment.TicketingSystem.DataModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetMyTicketDto {

    private TicketDetails ticketDetails;
    private  Message  message;



}
