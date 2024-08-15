package com.example.PharmaFinderAPI.entity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String username;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false)
    private Date dob;

    @Column(nullable = false, length = 15)
    private String mobileNumber;

    @Column(nullable = false, length = 255)
    private String address;

    @Column(nullable = false, length = 10)
    private String pincode;
}