package com.Assignment.TicketingSystem.Repositorys;

import com.Assignment.TicketingSystem.DataModels.UsersDetails;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface UsersRepository extends ReactiveMongoRepository<UsersDetails,String> {

    @Query("{'emailID':?0}")
    Mono<UsersDetails> findByEmail(String emailID);

}
