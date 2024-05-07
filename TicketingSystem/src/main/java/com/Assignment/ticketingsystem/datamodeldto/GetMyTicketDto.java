package com.Assignment.ticketingsystem.datamodeldto;

import com.Assignment.ticketingsystem.datamodel.Message;
import com.Assignment.ticketingsystem.datamodel.TicketDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetMyTicketDto {



    private TicketDetails ticketDetails;
    private Message message;





}
