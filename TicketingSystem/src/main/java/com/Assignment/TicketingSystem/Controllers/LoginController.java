package com.Assignment.TicketingSystem.Controllers;

import com.Assignment.TicketingSystem.DataModelDtos.UsersDetailsDto;
import com.Assignment.TicketingSystem.DataModels.UsersDetails;
import com.Assignment.TicketingSystem.Services.LoginService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/accountcontroller")
public class LoginController {
    @Autowired
    LoginService ticketloginservice;

    @PostMapping("/create")
    public Mono<String > createUser(@Valid @RequestBody
                                    UsersDetailsDto userdetailsdto) {
        return ticketloginservice.createAccount(userdetailsdto);
    }

    @PutMapping("/update-user")
    public Mono<String> updateUser(@RequestBody @Valid
                                        UsersDetailsDto userdetailsdto) {
        return ticketloginservice.updateUser(userdetailsdto);
    }

    @DeleteMapping("/delete-user/{adminid}")
    public Mono<String> deleteUser(@PathVariable @Email String adminid, @RequestBody @Email String  userEmail) {
        return ticketloginservice.deactivateAccount(adminid,userEmail);
    }


    @GetMapping("/getall")
    public Flux<UsersDetails> getall(){
        return ticketloginservice.find();
    }

}
