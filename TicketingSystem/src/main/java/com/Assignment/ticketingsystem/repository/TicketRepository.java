package com.Assignment.ticketingsystem.repository;

import com.Assignment.ticketingsystem.datamodel.TicketDetails;
import com.Assignment.ticketingsystem.datamodeldto.TicketAggregationDto;
import com.Assignment.ticketingsystem.datamodeldto.TicketDetailsDto;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface TicketRepository extends ReactiveMongoRepository<TicketDetails,String> {
    Mono<TicketDetails> getByTicketId(String ticketId);

   }
