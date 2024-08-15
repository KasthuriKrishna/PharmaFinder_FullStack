package com.example.PharmaFinderAPI.dto;
import java.util.Date;

import jakarta.persistence.Column;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

	private Long id;
    private String username;
    private String email;
    private Date dob;
    private String mobileNumber;
    private String address;
    private String pincode;
}
