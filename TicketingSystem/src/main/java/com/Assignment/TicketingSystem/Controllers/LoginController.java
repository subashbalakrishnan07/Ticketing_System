package com.Assignment.TicketingSystem.Controllers;

import com.Assignment.TicketingSystem.DataModelDtos.UserDetailsDto;
import com.Assignment.TicketingSystem.Services.LoginService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/account")
public class LoginController {
    @Autowired
    LoginService ticketLoginService;

    @PostMapping("/create-account")
    public Mono<String > createUser(@Valid @RequestBody
                                    UserDetailsDto userDetailsDto) {
        return ticketLoginService.createAccount(userDetailsDto);
    }

    @PutMapping("/update-account")
    public Mono<String> updateUser(@RequestBody @Valid
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
