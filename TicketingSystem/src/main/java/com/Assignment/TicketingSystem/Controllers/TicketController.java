package com.Assignment.TicketingSystem.Controllers;


import com.Assignment.TicketingSystem.DataModelDtos.TicketDetailsDto;
import com.Assignment.TicketingSystem.Services.TicketService;
import jakarta.validation.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Validated
@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    TicketService ticketService;

     @PostMapping("/create-ticket/{userEmail}")
    public Mono<String> createTicket(@PathVariable @Email String userEmail,
                                     @RequestBody @Validated(TicketDetailsDto.TicketCreateValidation.class)
                                     TicketDetailsDto ticketDetailsDto){
         return ticketService.createTicket(userEmail,ticketDetailsDto);
     }
}
