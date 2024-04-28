package com.Assignment.TicketingSystem.Repositorys;

import com.Assignment.TicketingSystem.DataModels.TicketDetails;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface TicketRepository extends ReactiveMongoRepository<TicketDetails,String> {
    Mono<TicketDetails> getByTicketId(String ticketId);
}
