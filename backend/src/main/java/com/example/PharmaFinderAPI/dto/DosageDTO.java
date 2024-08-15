package com.example.PharmaFinderAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DosageDTO {
	private Long id;
    private String dosage;
    private double cost;
}
