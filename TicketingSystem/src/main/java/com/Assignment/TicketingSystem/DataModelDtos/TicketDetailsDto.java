package com.Assignment.TicketingSystem.DataModelDtos;


import com.Assignment.TicketingSystem.Enums.TicketPriority;
import com.Assignment.TicketingSystem.Enums.TicketStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class TicketDetailsDto {

    public  static interface TicketCreateValidation{}
    public  static interface TicketRetrieveValidation{}

    @NotBlank(groups = {TicketRetrieveValidation.class})
    String ticketId;

    @NotBlank(groups = {TicketCreateValidation.class})
    String subject;

    @NotBlank(groups ={TicketCreateValidation.class})
    TicketPriority ticketPriority;

//    @Pattern(regexp="TICKET_RAISED|PROCESSING|RESOLVED|CLOSED",
//            groups = {TicketCreateValidation.class})
    TicketStatus ticketStatus;


}
