package com.Assignment.TicketingSystem.DataModelDtos;

import com.Assignment.TicketingSystem.DataModels.UserDetails;
import com.Assignment.TicketingSystem.Enums.UserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsDto {

    @NotBlank(message = "Name must not be blank")
    @Pattern(regexp = "^[a-zA-Z]+$",message = "Name should be Alphabet")
    private String name;

    @NotBlank(message = "Email must not be blank")
    @Email(message = "Email must be valid")
    private String emailID;

    @Pattern(regexp = "\\d{10}", message = "Mobile number should be 10 digits")
    private String mobileNumber;

    @NotNull(message = "User type must not be null")
    private UserType userType;

    public UserDetailsDto(UserDetails user) {
           this.name= user.getName();
           this.emailID= user.getEmailID();
           this.userType=user.getUserType();
           this.mobileNumber= user.getMobileNumber();


    }


}
