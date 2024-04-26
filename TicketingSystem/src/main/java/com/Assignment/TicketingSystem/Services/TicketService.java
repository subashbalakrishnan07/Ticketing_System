package com.Assignment.TicketingSystem.Services;

import com.Assignment.TicketingSystem.Enums.UserType;
import com.Assignment.TicketingSystem.DataModels.TicketDetails;
import com.Assignment.TicketingSystem.DataModelDtos.TicketDetailsDto;
import com.Assignment.TicketingSystem.Repositorys.TicketRepository;
import com.Assignment.TicketingSystem.Repositorys.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;
import java.util.function.Function;
import java.util.function.Supplier;

@Service
public class TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    UsersRepository usersRepository;

    Function<TicketDetailsDto, TicketDetails> dtotodetails = (ticketDetailsDto
            -> new TicketDetails(ticketDetailsDto));

    public Mono<String> createTicket(String emailId, TicketDetailsDto ticketDetailsDto) {
       Supplier<String> supplier= ()->{
        while (true) {
            System.out.println("sss");
            String uuid = UUID.randomUUID().toString().toLowerCase().substring(0,10);
            if(containsId(uuid)
                    .equals(Mono.just(true)))
                return uuid.toString().toLowerCase().substring(0,10);
            else
                continue;
        } };
        String id=supplier.get();
        ticketDetailsDto.setTicketId(id);

        return usersRepository.findByEmail(emailId).flatMap(user -> {
            if (user.isUserActive() && user.getUserType() == UserType.CUSTOMER) {
                return ticketRepository.save(dtotodetails
                        .apply(ticketDetailsDto))
                        .thenReturn("Ticket Created Successfully " + id);
            } else {
                return Mono.just("You are not a valid user to create a ticket");
            }
        }).switchIfEmpty(Mono.just("You are not a user to create a ticket"));
    }

    public Mono<Object> containsId(String ticketID) {
        return  ticketRepository
                .getByTicketId(ticketID)
                .hasElement()
                .map(hasElement -> hasElement ? false : true);
    }




}