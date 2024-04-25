package com.Assignment.TicketingSystem.Repository.DataModelDto;

import com.Assignment.TicketingSystem.Essential.UserType;
import com.Assignment.TicketingSystem.Repository.DataModel.UserDetails;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDetailsDto {

    @NotBlank(message = "Name Not  be blank ")
    @Pattern(regexp = "^[a-zA-Z]+$")
    String name;





    @Email(message = "Email id must be valid")
    private String emailID;
    @Pattern(regexp = "\\^d(10)$",message = "mobile should be 10 Digits")
     private  String mobileNumber;

    @Valid
  private  UserType userType;


    public UserDetailsDto(UserDetails user) {
        this.name = user.getName();
        this.emailID = user.getEmailID();
        this.userType = user.getUserType();
        this.mobileNumber = user.getMobileNumber();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "UserDetailsDto{" +
                "name='" + name + '\'' +
                ", emailID='" + emailID + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", userType=" + userType +
                '}';
    }
}
