package com.example.PharmaFinderAPI.service;
import com.example.PharmaFinderAPI.dto.MedicineDTO;
import com.example.PharmaFinderAPI.dto.PharmacyDTO;

import java.util.List;

public interface MedicineService {
    MedicineDTO createMedicine(MedicineDTO medicineDTO);
    MedicineDTO addOrUpdateUniqueMedicine(MedicineDTO medicineDTO);
    List<MedicineDTO> getAllMedicines();
    MedicineDTO getMedicineById(Long id);
    List<PharmacyDTO> findPharmaciesByMedicineAndPincode(long id, String userPincode);
    List<MedicineDTO> getMedicinesByCategory(String category);
    List<String> getDistinctCategories();
}
