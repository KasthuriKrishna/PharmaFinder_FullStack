package com.example.PharmaFinderAPI.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MedicineDTO {
    private Long id;
    private String name;
    private String purpose;
    private String category;
    private String brand;
    private double cost;	
    private Long pharmacyId; // Add this field
    private int quantity;
    private List<DosageDTO> dosages;
    private String form;
}
