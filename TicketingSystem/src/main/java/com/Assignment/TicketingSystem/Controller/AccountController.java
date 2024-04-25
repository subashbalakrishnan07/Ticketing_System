package com.Assignment.TicketingSystem.Controller;

import com.Assignment.TicketingSystem.Repository.DataModelDto.UserDetailsDto;
import com.Assignment.TicketingSystem.Service.TicketLoginService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    TicketLoginService ticketloginservice;

    @PostMapping("/create-user")
    private Mono<String> createUser(@RequestBody @Valid UserDetailsDto userdetailsdto) {
        return ticketloginservice.createAccount(userdetailsdto);
    }

    @PutMapping("/update-user")
    private Mono<String> updateUser(@RequestBody @Valid UserDetailsDto userdetailsdto) {
        return ticketloginservice.updateUser(userdetailsdto);
    }

    @DeleteMapping("/delete-user/{adminId}/{userId}")
    private Mono<String> deleteUser(@PathVariable String adminId, @PathVariable String userId) {
        return ticketloginservice.deactivateAccount(adminId, userId);
    }
}
