package com.example.PharmaFinderAPI.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StocksDTO {
    private Long id;
    private Long pharmacyId;
    private Long medicineId;
    private int quantity;
}
