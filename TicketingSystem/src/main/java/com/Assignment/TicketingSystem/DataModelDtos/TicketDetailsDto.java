package com.Assignment.TicketingSystem.DataModelDtos;


import com.Assignment.TicketingSystem.DataModels.TicketDetails;
import com.Assignment.TicketingSystem.Enums.TicketPriority;
import com.Assignment.TicketingSystem.Enums.TicketStatus;
import com.Assignment.TicketingSystem.ExceptionHandlers.CustomException;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class TicketDetailsDto {


    public TicketDetailsDto(TicketDetails ticketDetails) {
        this.emailId=ticketDetails.getEmailId();
        this.ticketId=ticketDetails.getTicketId();
        this.subject=ticketDetails.getSubject();
        this.ticketStatus=ticketDetails.getTicketStatus();
        this.ticketPriority=ticketDetails.getTicketPriority();

    }

    public  static interface TicketCreateValidation{}
    public  static interface TicketProcessingValidation{}
    public static interface TicketUpdateValidation{}

    @NotBlank(message = "Ticket id not Be Blank",groups = {TicketProcessingValidation.class,TicketUpdateValidation.class})
    private String ticketId;

    @NotBlank(message = "Ticket id not Be Blank",groups = {TicketCreateValidation.class,TicketUpdateValidation.class})
    private String subject;

    @NotNull(message = "Ticket id not Be Null",groups ={TicketCreateValidation.class,TicketUpdateValidation.class})
    private TicketPriority ticketPriority;

    @NotNull(message = "Ticket id not Be Null",groups={TicketProcessingValidation.class})
    private TicketStatus ticketStatus;

    @Email
    private String emailId;


}
