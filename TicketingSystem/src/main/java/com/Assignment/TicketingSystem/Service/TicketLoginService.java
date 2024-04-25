package com.Assignment.TicketingSystem.Service;


import com.Assignment.TicketingSystem.Essential.UserType;
import com.Assignment.TicketingSystem.Repository.DataModel.UserDetails;
import com.Assignment.TicketingSystem.Repository.DataModelDto.UserDetailsDto;
import com.Assignment.TicketingSystem.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
public class TicketLoginService {

    @Autowired
    UsersRepository userrepository;

    Function<UserDetailsDto, UserDetails> userdtotouser = UserDetails::new;

    Function<UserDetails, UserDetailsDto> usertouserdto = UserDetailsDto::new;


    public Mono<String> createAccount(UserDetailsDto userdetailsdto) {

        return userrepository.findByEmail(userdetailsdto.getEmailID()).flatMap(userDetails ->
                Mono.just("There is An Account Exist ").switchIfEmpty(userrepository.save(new UserDetails(userdetailsdto)).thenReturn("Accc")));


    }



    public Mono<String> updateUser(UserDetailsDto userdetailsdto) {

        return userrepository.findByEmail(userdetailsdto.getEmailID()).flatMap(user -> {
            user.setName(userdetailsdto.getName());
            user.setMobileNumber(userdetailsdto.getMobileNumber());
            return userrepository.save(user).thenReturn("User details Update Successfully");
        }).switchIfEmpty(Mono.just("There is no user"));
    }


    public Mono<String> deactivateAccount(String adminId, String userId) {

        return userrepository.findByEmail(adminId)
                .flatMap(adminuser -> {
                    if (adminuser.getUserType() == UserType.ADMIN && adminuser.isUserActive()) {
                        return userrepository.findByEmail(userId)
                                .flatMap(customer -> {
                                    if (customer.isUserActive()) {
                                        customer.setUserActive(false);
                                        return userrepository.save(customer).thenReturn("Account Deactivated Successfully");
                                    } else {
                                        return Mono.just("There is no active user with the provided email");
                                    }
                                })
                                .switchIfEmpty(Mono.just("There is no user with the provided email"));
                    } else {
                        return Mono.just("There is no active admin user with the provided email");
                    }
                })
                .switchIfEmpty(Mono.just("There is no admin user with the provided email"));
    }
 }
