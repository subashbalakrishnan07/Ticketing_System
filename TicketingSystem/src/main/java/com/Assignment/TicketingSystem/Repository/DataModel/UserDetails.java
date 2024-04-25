package com.Assignment.TicketingSystem.Repository.DataModel;

import com.Assignment.TicketingSystem.Repository.DataModelDto.UserDetailsDto;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.Assignment.TicketingSystem.Essential.UserType;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "UsersDetails")
public class UserDetails {
    @Getter
    @Id
   private String _id;
   @Setter
   @Getter
   private String name;
   @Getter
   private String emailID;
   @Getter
   @Setter
   private String mobileNumber;
   @Setter
   @Getter
   private UserType userType;
   private boolean isUserActive=true;

    public UserDetails(UserDetailsDto user) {

        this.name= user.getName();
        this.emailID=user.getEmailID();
        this.mobileNumber=user.getMobileNumber();
        this.userType=user.getUserType();
        boolean isUserActive=true;

    }

    @Override
    public String toString() {
        return "UserDetails{" +
                "_id='" + _id + '\'' +
                ", name='" + name + '\'' +
                ", emailID='" + emailID + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", userType=" + userType +
                ", isUserActive=" + isUserActive +
                '}';
    }

    public boolean isUserActive() {
        return isUserActive;
    }

    public void setUserActive(boolean userActive) {
        isUserActive = userActive;
    }


}
