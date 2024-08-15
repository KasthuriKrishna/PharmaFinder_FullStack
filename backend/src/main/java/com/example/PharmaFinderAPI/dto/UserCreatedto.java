package com.example.PharmaFinderAPI.dto;

import java.util.Date;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserCreatedto{

    private String username;
    private String email;
    private String password;
    private Date dob;
    private String mobileNumber;
    private String address;
    private String pincode;
}
