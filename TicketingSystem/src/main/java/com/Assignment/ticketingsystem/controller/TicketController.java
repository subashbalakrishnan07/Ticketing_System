package com.Assignment.ticketingsystem.controller;


import com.Assignment.ticketingsystem.datamodeldto.TicketAggregationDto;
import com.Assignment.ticketingsystem.datamodeldto.TicketDetailsDto;
import com.Assignment.ticketingsystem.datamodeldto.GetMyTicketDto;
import com.Assignment.ticketingsystem.service.TicketService;
import jakarta.validation.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Validated
@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    TicketService ticketService;


    @PostMapping("/create-ticket")
    public Mono<String> createTicket(@RequestBody @Validated(TicketDetailsDto.TicketCreateValidation.class  )
                                     TicketDetailsDto ticketDetailsDto) {
        return ticketService.createTicket(ticketDetailsDto);
    }


    @PutMapping("/update-ticket")
    public Mono<String> updateTicket(@RequestBody @Validated(TicketDetailsDto.TicketUpdateValidation.class)
                                     TicketDetailsDto ticketDetailsDto) {
        return ticketService.updateTicket(ticketDetailsDto);
    }

    @GetMapping("/get-my-ticket/{emailId}/{ticketId}")
    public Mono<GetMyTicketDto> getMyTicket(@PathVariable String emailId, @PathVariable String ticketId) {

        return ticketService.getMyTicket(emailId, ticketId);

    }

    @PutMapping("/add-reply/{emailId}/{ticketId}")
    public Mono<String> addReply(@PathVariable @Email String emailId,
                                 @PathVariable String ticketId,
                                 @RequestBody String message) {

        return ticketService.addMessage(emailId, ticketId, message);
    }


    @PutMapping("/ticket-processing/{emailId}")
    public Mono<String> processTicket(@Validated(TicketDetailsDto.TicketProcessingValidation.class)
                                      @PathVariable String emailId, @RequestBody TicketDetailsDto ticketDetailsDto) {
        return ticketService.processTickets(emailId, ticketDetailsDto);

    }

    @GetMapping("/get-tickets/{emailID}")
    public Flux<TicketDetailsDto> getTicket(@RequestBody TicketAggregationDto ticketAggregationDto) {
        return ticketService.getMatchedTickets(ticketAggregationDto);
    }
}
