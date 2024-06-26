package com.Assignment.ticketingsystem.datamodel;

import com.Assignment.ticketingsystem.enumpackage.TicketPriority;
import com.Assignment.ticketingsystem.enumpackage.TicketStatus;
import com.Assignment.ticketingsystem.datamodeldto.TicketDetailsDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "TicketDetails")
public class TicketDetails {



	@Id
	private String ticketId;
	private String subject;
	private TicketPriority ticketPriority;
	private TicketStatus ticketStatus;
	private String emailId;


	public TicketDetails(TicketDetailsDto ticketDetailsDto){

		this.ticketId= ticketDetailsDto.getTicketId();
		this.subject=ticketDetailsDto.getSubject();
		this.ticketPriority=ticketDetailsDto.getTicketPriority();
		this.ticketStatus=TicketStatus.TICKET_RAISED;
        this.emailId=ticketDetailsDto.getEmailId();


	}






	}
