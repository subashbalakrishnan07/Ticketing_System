package com.Assignment.TicketingSystem.Services;


import com.Assignment.TicketingSystem.DataModelDtos.UserDetailsDto;
import com.Assignment.TicketingSystem.DataModels.TicketDetails;
import com.Assignment.TicketingSystem.DataModels.UserDetails;
import com.Assignment.TicketingSystem.Enums.UserType;
import com.Assignment.TicketingSystem.ExceptionHandlers.CustomException;
import com.Assignment.TicketingSystem.ExceptionHandlers.UserNotFoundException;
import com.Assignment.TicketingSystem.Repositorys.SequenceRepository;
import com.Assignment.TicketingSystem.Repositorys.UsersRepository;
import jakarta.validation.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.transaction.xa.XAException;

@Service

public class LoginService {



    @Autowired
    UsersRepository userRepository;

    @Autowired
    SequenceRepository sequenceRepository;

    public Mono<String> createAccount(UserDetailsDto userDetailsDto) {
        return sequenceRepository.findById("662f8bfd2b87892de4820718")
                .flatMap(sequence -> {
                    userDetailsDto.setId(String.valueOf(sequence.getUserCount() + 1));
                    sequence.setUserCount(sequence.getUserCount() + 1);

                    return userRepository.findByEmail(userDetailsDto.getEmailID())
                            .flatMap(existingUser -> Mono.error(new CustomException("User already exists with email: " + userDetailsDto.getEmailID())))
                            .switchIfEmpty(userRepository.save(new UserDetails(userDetailsDto))
                                    .then(sequenceRepository.save(sequence)
                                            .thenReturn("Account created successfully")));
                })
                .onErrorResume(throwable -> {
                    if (throwable instanceof RuntimeException) {
                        return Mono.error(throwable);
                    } else {
                        // Handle other types of errors if necessary
                        return Mono.error(  new CustomException("User already Exist"));
                    }
                })
                .map(Object::toString); // Ensure the final result is converted to String
    }
//    public Mono<String> createAccount(UserDetailsDto userDetailsDto) {
//
//        return sequenceRepository.findAllById("662f8bfd2b87892de4820718").flatMap(sequence -> {
//                    userDetailsDto.setId(String.valueOf(sequence.getUserCount() + 1));
//                    sequence.setUserCount(sequence.getUserCount() + 1);
//                    return userRepository.findByEmail(userDetailsDto.getEmailID())
//                            .switchIfEmpty(userRepository.save(new UserDetails(userDetailsDto)).then(
//                                    sequenceRepository.save(sequence).thenReturn("aasad")
//                                    ))
//                });

            //))

//            (userRepository.save(new UserDetails(userDetailsDto)).flatMap(s->
//            {
//
//            }));
//        }).switchIfEmpty(Mono.error(new CustomException("Internal Server Error")));



//        return userRepository.save(new UserDetails(userDetailsDto))
//                .flatMap(userDetails -> sequenceRepository.findAllById("662f8bfd2b87892de4820718"))
//                .flatMap(sequence -> {
//                    UserDetails newUserDetails = new UserDetails(userDetailsDto);
//                    newUserDetails.setId(String.valueOf(sequence.getUserCount() + 1));
//                    sequence.setUserCount(sequence.getUserCount() + 1);
//                    return sequenceRepository.save(sequence).thenReturn("Created Successfully");
//                });


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
