package com.Assignment.TicketingSystem.Services;


import com.Assignment.TicketingSystem.DataModelDtos.UsersDetailsDto;
import com.Assignment.TicketingSystem.DataModels.UsersDetails;
import com.Assignment.TicketingSystem.Enums.UserType;
import com.Assignment.TicketingSystem.ExceptionHandlers.CustomException;
import com.Assignment.TicketingSystem.ExceptionHandlers.UserNotFoundException;
import com.Assignment.TicketingSystem.Repositorys.UsersRepository;
import jakarta.validation.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service

public class LoginService {

    @Autowired
    UsersRepository userrepository;

    public Mono<String> createAccount( UsersDetailsDto userdetailsdto) {
        return userrepository.findByEmail(userdetailsdto.getEmailID())
               .flatMap((user)-> {
                   System.out.println(user.toString());
                 return   Mono.error(new CustomException("User Already Exists"));
               })
                .switchIfEmpty(userrepository.save(new UsersDetails(userdetailsdto)))
                .thenReturn("Created SuccessFully");
    }

    public Mono<String> updateUser( UsersDetailsDto userdetailsdto) {

        return userrepository.findByEmail(userdetailsdto.getEmailID())
                .flatMap(user-> {
                     user.setName(userdetailsdto.getName());
                     user.setMobileNumber(userdetailsdto.getMobileNumber());
                    userrepository.save(user);
                    return Mono.just("Account Update Successfully");
                })
                .switchIfEmpty( Mono.error(new CustomException("User not Exists")));
    }

public Flux<UsersDetails> find(){
        return userrepository.findAll();
}


    public Mono<String> deactivateAccount(@Email String adminEmailId,
                                          @Email String userEmailId) {

        return  userrepository.findByEmail(adminEmailId).flatMap(adminUser->{
            if(adminUser.isUserActive()&& adminUser.getUserType()==UserType.ADMIN){
                userrepository.findByEmail(userEmailId).flatMap(userDetails -> {
                            if(userDetails.isUserActive()&&userDetails.getUserType()==UserType.CUSTOMER){
                                userDetails.setUserActive(false);
                                userrepository.save(userDetails)
                                               .thenReturn("User deactivate SuccessFully");}
                            return Mono.error(new UserNotFoundException("No user Found"));
                 }).switchIfEmpty(Mono.error(new UserNotFoundException("No user Found")));
            }
            return Mono.error(new UserNotFoundException("No admin user Found"));
        });
    }
 }
