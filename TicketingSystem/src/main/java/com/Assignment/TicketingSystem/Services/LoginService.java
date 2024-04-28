package com.Assignment.TicketingSystem.Services;


import com.Assignment.TicketingSystem.DataModelDtos.UserDetailsDto;
import com.Assignment.TicketingSystem.DataModels.UserDetails;
import com.Assignment.TicketingSystem.Enums.UserType;
import com.Assignment.TicketingSystem.ExceptionHandlers.CustomException;
import com.Assignment.TicketingSystem.ExceptionHandlers.UserNotFoundException;
import com.Assignment.TicketingSystem.Repositorys.UsersRepository;
import jakarta.validation.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service

public class LoginService {

    @Autowired
    UsersRepository userRepository;

    public Mono<String> createAccount(UserDetailsDto userDetailsDto) {
        return userRepository.findByEmail(userDetailsDto.getEmailID())
               .flatMap((usersDetails)->
                 Mono.error(new CustomException("User Already Exists"))
               )
                .switchIfEmpty(userRepository.save(new UserDetails(userDetailsDto)))
                .thenReturn("Created SuccessFully");
    }

    public Mono<String> updateUser( UserDetailsDto userDetailsDto) {
        return userRepository.findByEmail(userDetailsDto.getEmailID())
                .flatMap(user-> {
                     user.setName(userDetailsDto.getName());
                     user.setMobileNumber(userDetailsDto.getMobileNumber());
                   return  userRepository.save(user).thenReturn("Account Update Successfully");
                })
                .switchIfEmpty(Mono.error(new CustomException("User not Exists")));
    }

    public Flux<UserDetailsDto> getAllAccounts(String emailID) {
        return userRepository.findByEmail(emailID)
                .filter(users -> users.isUserActive() && users.getUserType() == UserType.ADMIN)
                .flatMapMany(users -> userRepository.findAll()
                        .map(UserDetailsDto::new)
                )
                .switchIfEmpty(Flux.error(new CustomException("No User Found")));
    }


    public Mono<String> deactivateAccount(@Email String adminEmailId,@Email String userEmailId) {
        return  userRepository.findByEmail(adminEmailId).flatMap(adminUser->{
            if( adminUser.isUserActive() && adminUser.getUserType()==UserType.ADMIN){
                return userRepository.findByEmail(userEmailId).flatMap(userDetails -> {
                            if(userDetails.isUserActive()&&userDetails.getUserType()==UserType.CUSTOMER){
                                userDetails.setUserActive(false);
                               return  userRepository.save(userDetails)
                                               .thenReturn("User deactivate SuccessFully");}
                            return Mono.error(new UserNotFoundException("No user Found"));
                 }).switchIfEmpty(Mono.error(new UserNotFoundException("No user Found")));
            }
            return Mono.error(new UserNotFoundException("No admin user Found"));
        });
    }
 }
