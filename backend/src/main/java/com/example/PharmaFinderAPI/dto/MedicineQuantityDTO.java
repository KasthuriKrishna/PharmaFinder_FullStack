package com.example.PharmaFinderAPI.dto;
import com.example.PharmaFinderAPI.entity.Medicine;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedicineQuantityDTO {
    private Medicine medicine;
    private int quantity;
}
