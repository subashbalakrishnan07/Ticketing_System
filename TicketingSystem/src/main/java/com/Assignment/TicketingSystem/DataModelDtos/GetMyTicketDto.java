package com.Assignment.TicketingSystem.DataModelDtos;

import com.Assignment.TicketingSystem.DataModels.Message;
import com.Assignment.TicketingSystem.DataModels.TicketDetails;
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
