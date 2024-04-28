package com.Assignment.TicketingSystem.Repositorys;

import com.Assignment.TicketingSystem.DataModels.Message;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface TicketMessageRepository extends ReactiveMongoRepository<Message,String> {

   public Mono<Message>  getByTicketId(String ticketId);

}
