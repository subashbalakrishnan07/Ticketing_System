package com.Assignment.ticketingsystem.datamodeldto;

import com.Assignment.ticketingsystem.datamodel.UserDetails;
import com.Assignment.ticketingsystem.enumpackage.UserType;
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

    private String id;

    @NotBlank(message = "Name must not be blank",groups = {UserDetailsDto.CreateValidationGroup.class,UserDetailsDto.UpdateValidationGroup.class})
    @Pattern(regexp = "^[a-zA-Z]+$",message = "Name should be Alphabet",groups = {UserDetailsDto.CreateValidationGroup.class,UserDetailsDto.UpdateValidationGroup.class})
    private String name;

    @NotBlank(message = "Email must not be blank",groups = {UserDetailsDto.CreateValidationGroup.class})
    @Email(message = "Email must be valid",groups = {UserDetailsDto.CreateValidationGroup.class,UserDetailsDto.UpdateValidationGroup.class})
    private String emailID;

    @Pattern(regexp = "\\d{10}", message = "Mobile number should be 10 digits",groups = {UserDetailsDto.CreateValidationGroup.class,UserDetailsDto.UpdateValidationGroup.class})
    private String mobileNumber;

    @NotNull(message = "User type must not be null",groups = {UserDetailsDto.CreateValidationGroup.class})
    private UserType userType;

    public UserDetailsDto(UserDetails user) {
           this.name= user.getName();
           this.emailID= user.getEmailID();
           this.userType=user.getUserType();
           this.mobileNumber= user.getMobileNumber();


    }

    public interface CreateValidationGroup{}
    public interface UpdateValidationGroup{}


}
