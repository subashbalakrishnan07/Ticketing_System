package com.Assignment.TicketingSystem.DataModelDtos;


import com.Assignment.TicketingSystem.Enums.TicketPriority;
import com.Assignment.TicketingSystem.Enums.TicketStatus;
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

    public  static interface TicketCreateValidation{}
    public  static interface TicketProcessingValidation{}
    public static interface TicketUpdateValidation{}

    @NotBlank(groups = {TicketProcessingValidation.class,TicketUpdateValidation.class})
    private String ticketId;

    @NotBlank(groups = {TicketCreateValidation.class,TicketUpdateValidation.class})
    private String subject;

    @NotNull(groups ={TicketCreateValidation.class,TicketUpdateValidation.class})
    private TicketPriority ticketPriority;

    @NotNull(groups={TicketProcessingValidation.class})
    private TicketStatus ticketStatus;

    @Email
    private String emailId;


}
