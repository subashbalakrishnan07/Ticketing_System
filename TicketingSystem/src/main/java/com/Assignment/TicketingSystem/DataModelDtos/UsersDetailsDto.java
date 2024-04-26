package com.Assignment.TicketingSystem.DataModelDtos;

//import com.Assignment.TicketingSystem.Enums.UserType;
//import com.Assignment.TicketingSystem.DataModels.UserDetails;
//import jakarta.validation.constraints.Email;
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.Pattern;
//import lombok.*;
//import org.springframework.validation.annotation.Validated;
//
////@Data
////@AllArgsConstructor
////@NoArgsConstructor
//
//public class UserDetailsDto {
//        //Marker Interface for Validations
////    public static interface CreateValidation {}
////    public static interface UpdateValidationGroup {}
////    public static interface GmailValidation{}
//
//    @NotBlank(message = "Name Not  be blank ")
////    @Pattern(regexp = "^[a-zA-Z]+$",message = "Used be Alphabet")
//    String name;
//
//    @NotBlank(message = "Email id must be valid")
////    @Email(message = "Email id must be valid")
//    private String emailID;
//
////     @Pattern(regexp = "\\^d(10)$",message = "mobile should be 10 Digits")
//     private  String mobileNumber;
//
////    @Pattern(regexp = "ADMIN|CUSTOMER",message = "MUST BE CUSTOMER or ADMIN")
//    @NotBlank(message = "not blank")
//    private  UserType userType;
//
//    public UserDetailsDto() {
//    }
//
//    public UserDetailsDto(String name, String emailID, String mobileNumber, UserType userType) {
//        this.name = name;
//        this.emailID = emailID;
//        this.mobileNumber = mobileNumber;
//        this.userType = userType;
//    }
//
//    public UserDetailsDto(UserDetails user) {
//        this.name = user.getName();
//        this.emailID = user.getEmailID();
//        this.userType = user.getUserType();
//        this.mobileNumber = user.getMobileNumber();
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getMobileNumber() {
//        return mobileNumber;
//    }
//
//    public void setMobileNumber(String mobileNumber) {
//        this.mobileNumber = mobileNumber;
//    }
//
//    public UserType getUserType() {
//        return userType;
//    }
//
//    public void setUserType(UserType userType) {
//        this.userType = userType;
//    }
//
//    public String getEmailID() {
//        return emailID;
//    }
//
//    public void setEmailID(String emailID) {
//        this.emailID = emailID;
//    }
//
//    @Override
//    public String toString() {
//        return "UserDetailsDto{" +
//                "name='" + name + '\'' +
//                ", emailID='" + emailID + '\'' +
//                ", mobileNumber='" + mobileNumber + '\'' +
//                ", userType=" + userType +
//                '}';
//    }
//}

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
public class UsersDetailsDto {

    @NotBlank(message = "Name must not be blank")
    @Pattern(regexp = "^[a-zA-Z]+$",message = "Used be Alphabet")
    private String name;

    @NotBlank(message = "Email must not be blank")
    @Email(message = "Email must be valid")
    private String emailID;

    @Pattern(regexp = "\\d{10}", message = "Mobile number should be 10 digits")
    private String mobileNumber;

    @NotNull(message = "User type must not be null")
    private UserType userType;

    @Override
    public String toString() {
        return "UsersDetailsDto{" +
                "name='" + name + '\'' +
                ", emailID='" + emailID + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", userType=" + userType +
                '}';
    }
}
