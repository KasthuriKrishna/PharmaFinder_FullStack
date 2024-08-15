package com.example.PharmaFinderAPI.dto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StockDetailsDTO {
    private Long id;
    private List<MedicineQuantityDTO> medicines;
}
