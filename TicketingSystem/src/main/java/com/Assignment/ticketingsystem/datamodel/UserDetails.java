package com.Assignment.ticketingsystem.datamodel;
import com.Assignment.ticketingsystem.datamodeldto.UserDetailsDto;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.Assignment.ticketingsystem.enumpackage.UserType;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "UsersDetails")
public class UserDetails {


    @Id
    private String id;
    private String name;
    private String emailID;

    private String mobileNumber;

    private UserType userType;

    private boolean isUserActive ;

    public UserDetails(UserDetailsDto user) {

        this.name = user.getName();
        this.emailID = user.getEmailID();
        this.mobileNumber = user.getMobileNumber();
        this.userType = user.getUserType();
        this.isUserActive = true;
    }



}
