package com.Assignment.TicketingSystem.DataModels;
import com.Assignment.TicketingSystem.DataModelDtos.UsersDetailsDto;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.Assignment.TicketingSystem.Enums.UserType;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "UsersDetails")
public class UsersDetails {


    @Id
    private String id;
    private String name;
    private String emailID;

    private String mobileNumber;

    private UserType userType;

    private boolean isUserActive = true;

    public UsersDetails(UsersDetailsDto user) {

        this.name = user.getName();
        this.emailID = user.getEmailID();
        this.mobileNumber = user.getMobileNumber();
        this.userType = user.getUserType();
        boolean isUserActive = true;
    }

    @Override
    public String toString() {
        return "UserDetails{" + "_id='" + id + ", name='" + name + ", emailID='" + emailID + ", mobileNumber='" + mobileNumber + ", userType=" + userType + ", isUserActive=" + isUserActive + '}';
    }


}
