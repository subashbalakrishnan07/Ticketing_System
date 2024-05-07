package com.Assignment.ticketingsystem.datamodel;

import com.Assignment.ticketingsystem.enumpackage.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection= "TicketMessage")
public class Message {

	@Id
	private String ticketId;
	private List<MessageBody>  msglist;


	@AllArgsConstructor
	@NoArgsConstructor
	@Data
	static class  MessageBody {
		 private int s_no;
	     private String message;
	     private UserType usertype;
		private Instant timeStamp;

	}

    public Message(String ticketId){
	   this.ticketId=ticketId;
	   msglist=new ArrayList<>();
    }

  public void addMessage(String message,UserType userType){
	   msglist.addLast(new MessageBody(msglist.size()+1,message,userType,Instant.now()));
   }

}
