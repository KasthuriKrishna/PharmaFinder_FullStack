package com.example.PharmaFinderAPI.dto;

import java.time.LocalDateTime;

import com.example.PharmaFinderAPI.entity.Medicine;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderHistoryDTO {
    
    private Long id;
    private UserDTO user;
    private Medicine medicine;
    private int quantity;
    private LocalDateTime orderDate;
}
