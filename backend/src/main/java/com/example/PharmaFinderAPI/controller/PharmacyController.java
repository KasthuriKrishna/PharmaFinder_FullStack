package com.example.PharmaFinderAPI.controller;
import com.example.PharmaFinderAPI.dto.PharmacyCreateDTO;
import com.example.PharmaFinderAPI.dto.PharmacyDTO;
import com.example.PharmaFinderAPI.dto.UserDTO;
import com.example.PharmaFinderAPI.service.PharmacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pharmacies")

public class PharmacyController {

    @Autowired
    private PharmacyService pharmacyService;

    // Endpoint to create a new pharmacy
    @PostMapping
    public PharmacyDTO createPharmacy(@RequestBody PharmacyCreateDTO pharmacyCreateDTO) {
        return pharmacyService.createPharmacy(pharmacyCreateDTO);
    }

    // Endpoint to get all pharmacies
    @GetMapping
    public List<PharmacyCreateDTO> getAllPharmacies() {
        return pharmacyService.getAllPharmacies();
    }

    // Endpoint to get pharmacy by ID
    @GetMapping("/{id}")
    public PharmacyDTO getPharmacyById(@PathVariable Long id) {
        return pharmacyService.getPharmacyById(id);
    }

    // Endpoint to get pharmacy details by ID (excluding password, license, business registration number, in-charge)
    @GetMapping("/details/{id}")
    public PharmacyDTO getPharmacyDetails(@PathVariable Long id) {
        return pharmacyService.getPharmacyDetails(id);
    }
    @GetMapping("/authenticate")
    public ResponseEntity<PharmacyDTO> authenticatePharma(
            @RequestParam("email") String email,
            @RequestParam("password") String password) {
        PharmacyDTO pharmacyDTO = pharmacyService.authenticatePharma(email, password);
        if (pharmacyDTO != null) {
            return ResponseEntity.ok(pharmacyDTO);
        } else {
            return ResponseEntity.status(401).build(); // Unauthorized
        }
    }
    
    //To fetch list of pharmacies in a specified area grouped by particular pincode
    @GetMapping("/by-pincode")
    public ResponseEntity<List<PharmacyDTO>> getPharmaciesByPincode(@RequestParam String pincode) {
        List<PharmacyDTO> pharmacies = pharmacyService.getPharmaciesByPincode(pincode);
        return ResponseEntity.ok(pharmacies);
    }

}
