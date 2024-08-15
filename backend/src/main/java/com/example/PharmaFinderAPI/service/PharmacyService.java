package com.example.PharmaFinderAPI.service;
import com.example.PharmaFinderAPI.dto.PharmacyCreateDTO;
import com.example.PharmaFinderAPI.dto.PharmacyDTO;
import com.example.PharmaFinderAPI.dto.UserDTO;

import java.util.List;

public interface PharmacyService {
    PharmacyDTO createPharmacy(PharmacyCreateDTO pharmacyCreateDTO);
    List<PharmacyCreateDTO> getAllPharmacies();
    PharmacyDTO getPharmacyById(Long id);
    PharmacyDTO getPharmacyDetails(Long id);
    PharmacyDTO authenticatePharma(String email, String password);
    List<PharmacyDTO> getPharmaciesByPincode(String pincode);
}
