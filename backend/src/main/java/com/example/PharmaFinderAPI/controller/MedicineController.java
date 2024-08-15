package com.example.PharmaFinderAPI.controller;

import com.example.PharmaFinderAPI.dto.MedicineDTO;
import com.example.PharmaFinderAPI.dto.PharmacyDTO;
import com.example.PharmaFinderAPI.entity.Medicine;
import com.example.PharmaFinderAPI.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicines")

public class MedicineController {
    @Autowired
    private MedicineService medicineService;
    
    //To add new medicines
    @PostMapping
    public ResponseEntity<MedicineDTO> createMedicine(@RequestBody MedicineDTO medicineDTO) {
        MedicineDTO createdMedicine = medicineService.createMedicine(medicineDTO);
        return ResponseEntity.ok(createdMedicine);
    }
    @PostMapping("/addunique")
    public ResponseEntity<MedicineDTO> addOrUpdateUniqueMedicine(@RequestBody MedicineDTO medicineDTO) {
        MedicineDTO updatedMedicine = medicineService.addOrUpdateUniqueMedicine(medicineDTO);
        return ResponseEntity.ok(updatedMedicine);
    }
    //View all the available medicines and dosage details
    @GetMapping
    public ResponseEntity<List<MedicineDTO>> getAllMedicines() {
        List<MedicineDTO> medicines = medicineService.getAllMedicines();
        return ResponseEntity.ok(medicines);
    }

    //View particular medicine and dosage details by specifying the id
    @GetMapping("/{id}")
    public ResponseEntity<MedicineDTO> getMedicineById(@PathVariable Long id) {
        MedicineDTO medicine = medicineService.getMedicineById(id);
        if (medicine != null) {
            return ResponseEntity.ok(medicine);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //find pharmacies with a particular medicine in the pincode same as user's pincode
    @GetMapping("/search")
    public ResponseEntity<List<PharmacyDTO>> findPharmaciesByMedicineAndPincode(
            @RequestParam long id,
            @RequestParam String userPincode) {
        List<PharmacyDTO> pharmacies = medicineService.findPharmaciesByMedicineAndPincode(id, userPincode);
        return ResponseEntity.ok(pharmacies);
    }
    
    //groups the medicines based on the purposes(fever,cough,ulcer)
    @GetMapping("/by-category")
    public ResponseEntity<List<MedicineDTO>> getMedicinesByCategory(@RequestParam String category) {
        List<MedicineDTO> medicines = medicineService.getMedicinesByCategory(category);
        return ResponseEntity.ok(medicines);
    }
    
    //displays the distinct categories of medicines so the users can select the required category
    @GetMapping("/categories")
    public ResponseEntity<List<String>> getDistinctCategories() {
        List<String> categories = medicineService.getDistinctCategories();
        return ResponseEntity.ok(categories);
    }
}
