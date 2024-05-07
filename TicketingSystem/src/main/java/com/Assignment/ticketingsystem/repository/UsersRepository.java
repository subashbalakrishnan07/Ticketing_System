package com.Assignment.ticketingsystem.repository;

import com.Assignment.ticketingsystem.datamodel.UserDetails;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface UsersRepository extends ReactiveMongoRepository<UserDetails,String> {

    @Query("{'emailID':?0}")
    Mono<UserDetails> findByEmail(String emailID);

}
