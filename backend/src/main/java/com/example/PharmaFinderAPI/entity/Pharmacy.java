package com.example.PharmaFinderAPI.entity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pharmacies")
public class Pharmacy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String pharmacyName;

    @Column(nullable = false, length = 255)
    private String address;

    @Column(nullable = false, length = 15)
    private String contact;

    @Column(nullable = false, length = 100)
    private String license;

    @Column(nullable = false, length = 50)
    private String inCharge;

    @Column(nullable = false, length = 100)
    private String businessRegistrationNumber;

    @Column(nullable = false)
    private LocalTime operatingHoursFrom;

    @Column(nullable = false)
    private LocalTime operatingHoursTo;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 10)
    private String pincode;
}