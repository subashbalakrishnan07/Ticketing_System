package com.Assignment.ticketingsystem.controller;

import com.Assignment.ticketingsystem.datamodeldto.UserDetailsDto;
///import com.Assignment.TicketingSystem.DataModelDtos.UserDetailsDto.CreateValidationGroup;
import com.Assignment.ticketingsystem.service.LoginService;
import jakarta.validation.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/account")
public class LoginController {
    @Autowired
    LoginService ticketLoginService;

    @PostMapping("/create-account")
    public Mono<?> createUser(@Validated(UserDetailsDto.CreateValidationGroup.class) @RequestBody
                                    UserDetailsDto userDetailsDto) {
        return ticketLoginService.createAccount(userDetailsDto);
    }

    @GetMapping("/simple-web-call")
    public Mono<String> simpleWebCall(){
        return Mono.just("Simple webclient call ");
    }


    @PutMapping("/update-account")
    public Mono<String> updateUser(@RequestBody @Validated
            (UserDetailsDto.UpdateValidationGroup.class)
                                        UserDetailsDto userDetailsDto) {
        return ticketLoginService.updateUser(userDetailsDto);
    }

    @DeleteMapping("/delete-account/{adminId}")
    public Mono<String> deactivateUser(@PathVariable @Email  String adminId,
                                   @RequestBody @Email String  userId) {
        return ticketLoginService.deactivateAccount(adminId,userId);
    }


    @GetMapping("/getall-accounts/{emailId}")
    public Flux<UserDetailsDto> getAllAccounts(@PathVariable String adminId){
        return ticketLoginService.getAllAccounts(adminId);
    }

}
