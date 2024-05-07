package com.Assignment.ticketingsystem.repository;

import com.Assignment.ticketingsystem.datamodel.Message;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface TicketMessageRepository extends ReactiveMongoRepository<Message,String> {

   public Mono<Message>  getByTicketId(String ticketId);

}
