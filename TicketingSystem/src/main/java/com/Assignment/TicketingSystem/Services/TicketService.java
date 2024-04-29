package com.Assignment.TicketingSystem.Services;

import com.Assignment.TicketingSystem.DataModelDtos.TicketAggregationDto;
import com.Assignment.TicketingSystem.DataModelDtos.TicketDetailsDto;
import com.Assignment.TicketingSystem.DataModelDtos.GetMyTicketDto;
import com.Assignment.TicketingSystem.DataModels.Message;
import com.Assignment.TicketingSystem.DataModels.TicketDetails;
import com.Assignment.TicketingSystem.Enums.TicketStatus;
import com.Assignment.TicketingSystem.Enums.UserType;
import com.Assignment.TicketingSystem.ExceptionHandlers.CustomException;
import com.Assignment.TicketingSystem.ExceptionHandlers.TicketNotFoundException;
import com.Assignment.TicketingSystem.ExceptionHandlers.UserNotFoundException;
import com.Assignment.TicketingSystem.Repositorys.SequenceRepository;
import com.Assignment.TicketingSystem.Repositorys.TicketMessageRepository;
import com.Assignment.TicketingSystem.Repositorys.TicketRepository;
import com.Assignment.TicketingSystem.Repositorys.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    TicketMessageRepository ticketMessageRepository;

    @Autowired
    SequenceRepository sequenceRepository;

    @Autowired
    ReactiveMongoTemplate reactiveMongoTemplate;



    public Mono<String> createTicket(TicketDetailsDto ticketDetailsDto) {


        return sequenceRepository.findAllById("662f8bfd2b87892de4820718").flatMap(sequence -> {
            ticketDetailsDto.setTicketId(String.valueOf(sequence.getTicketCount() + 1));
            sequence.setTicketCount(sequence.getTicketCount() + 1);
            return usersRepository.findByEmail(ticketDetailsDto.getEmailId()).flatMap(user -> {
                if (user.isUserActive() && user.getUserType() == UserType.CUSTOMER) {
                    return ticketRepository.save(new TicketDetails(ticketDetailsDto)).flatMap(s->
                            sequenceRepository.save(sequence)).thenReturn("Ticket created Successfully");
                } else {
                    return Mono.error(new CustomException("No User Found"));
                }
            }).switchIfEmpty(Mono.error(new CustomException("No User Found")));
        }).switchIfEmpty(Mono.error(new CustomException("Internal Server Error")));


    }


    public Mono<String> updateTicket(TicketDetailsDto ticketDetailsDto) {

        return ticketRepository.getByTicketId(ticketDetailsDto.getTicketId()).flatMap(ticket -> {
            if (ticket.getTicketStatus() != TicketStatus.CLOSED) {
                ticket.setTicketPriority(ticketDetailsDto.getTicketPriority());
                ticket.setSubject(ticketDetailsDto.getSubject());
                return ticketRepository.save(ticket).thenReturn("Ticket update SuccessFully");
            }
            return Mono.error(new TicketNotFoundException("Ticket not Fount to update"));
        }).switchIfEmpty(Mono.error(new TicketNotFoundException("Ticket not Fount to update")));
    }


    public Mono<GetMyTicketDto> getMyTicket(String emailId, String ticketid) {
        return usersRepository.findByEmail(emailId).flatMap(user -> {
            System.out.println(user.isUserActive() + " " + user.getUserType());
            if (user.isUserActive() && user.getUserType() == UserType.CUSTOMER) {
                return ticketRepository.getByTicketId(ticketid).flatMap(ticket -> {
                    if (ticket.getEmailId().equals(emailId)) {
                        System.out.println("i");
                        return ticketMessageRepository.getByTicketId(ticket.getTicketId()).flatMap(ticketmessage -> {
                            return Mono.just(new GetMyTicketDto(ticket, ticketmessage));
                        }).switchIfEmpty(Mono.error(new TicketNotFoundException("No message  found")));
                    }
                    return Mono.error(new TicketNotFoundException("Ticket not found"));
                });
            }
            return Mono.error(new UserNotFoundException("User Not Found"));
        }).switchIfEmpty(Mono.error(new UserNotFoundException(" switch User Not Found")));
    }


    public Mono<String> addMessage(String emailId, String ticketId, String messageLine) {

        return usersRepository.findByEmail(emailId).flatMap(user -> {
            if (!user.isUserActive()) {
                return Mono.error(new UserNotFoundException("User is not active"));
            }
            return ticketRepository.getByTicketId(ticketId).flatMap(ticketDetails -> {
                if (ticketDetails.getTicketStatus() == TicketStatus.CLOSED || ticketDetails.getTicketStatus() == TicketStatus.RESOLVED || !(user.getUserType() == UserType.ADMIN || ticketDetails.getEmailId().equals(emailId))) {
                    return Mono.error(new CustomException("Ticket not found or access denied"));
                }
                return ticketMessageRepository.getByTicketId(ticketId).switchIfEmpty(ticketMessageRepository.save(new Message(ticketId))).flatMap(message -> {
                    message.addMessage(messageLine, user.getUserType());
                    return ticketMessageRepository.save(message).thenReturn("Ticket Reply Added Successfully");
                });
            }).switchIfEmpty(Mono.error(new CustomException("Ticket not found")));
        }).switchIfEmpty(Mono.error(new UserNotFoundException("User not found")));
    }

    public Mono<String> processTickets(String emailId, TicketDetailsDto ticketDetailsDto) {
        return usersRepository.findByEmail(emailId).flatMap(user -> {
            if (!user.isUserActive() && user.getUserType() == UserType.CUSTOMER)
                return Mono.error(new UserNotFoundException("User No Found"));
            else {
                return ticketRepository.getByTicketId(ticketDetailsDto.getTicketId()).flatMap(ticket -> {
                    if (ticket.getTicketStatus() == TicketStatus.CLOSED)
                        return Mono.error(new CustomException("Ticket is Closed"));
                    else ticket.setTicketStatus(ticketDetailsDto.getTicketStatus());
                    return ticketRepository.save(ticket).thenReturn("Ticket Processing Update SuccessFully");
                }).switchIfEmpty(Mono.error(new TicketNotFoundException("Ticket Not Found")));


            }
        }).switchIfEmpty(Mono.error(new UserNotFoundException("User Not Found")));

    }

    public Flux<TicketDetailsDto> getMatchedTickets(TicketAggregationDto ticketAggregationDto) {
        MatchOperation matchOperation = Aggregation.match(
                new Criteria().orOperator(
                        Criteria.where("ticketPriority").in(ticketAggregationDto.getTicketPriorityList()),
                        Criteria.where("emailId").in(ticketAggregationDto.getEmailList()),
                        Criteria.where("subject").in(ticketAggregationDto.getSubjectList()),
                        Criteria.where("ticketStatus").in(ticketAggregationDto.getTicketStatusList())
                )
        );

        Aggregation aggregation = Aggregation.newAggregation(matchOperation);
            return
             reactiveMongoTemplate
             .aggregate(aggregation, "TicketDetails", TicketDetails.class)
             .switchIfEmpty(Flux.error(new CustomException("No Data Found")))
             .flatMap(ticketDetails -> Flux.just(new TicketDetailsDto(ticketDetails)));

    }


}