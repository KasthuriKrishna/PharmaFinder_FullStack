package com.example.PharmaFinderAPI.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PharmacyDTO {
    private Long id;
    private String pharmacyName;
    private String address;
    private String contact;
    private LocalTime operatingHoursFrom;
    private LocalTime operatingHoursTo;
    private String email;
    private String pincode;
}
