package com.Assignment.TicketingSystem.DataModels;
import com.Assignment.TicketingSystem.DataModelDtos.UserDetailsDto;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.Assignment.TicketingSystem.Enums.UserType;

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

    private boolean isUserActive = true;

    public UserDetails(UserDetailsDto user) {

        this.name = user.getName();
        this.emailID = user.getEmailID();
        this.mobileNumber = user.getMobileNumber();
        this.userType = user.getUserType();
        boolean isUserActive = true;
    }



}
