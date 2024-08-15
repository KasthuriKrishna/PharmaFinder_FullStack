package com.example.PharmaFinderAPI.service.impl;
import com.example.PharmaFinderAPI.dto.PharmacyCreateDTO;
import com.example.PharmaFinderAPI.dto.PharmacyDTO;
import com.example.PharmaFinderAPI.dto.UserDTO;
import com.example.PharmaFinderAPI.entity.Pharmacy;
import com.example.PharmaFinderAPI.entity.User;
import com.example.PharmaFinderAPI.repository.PharmacyRepo;
import com.example.PharmaFinderAPI.service.PharmacyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PharmacyServiceImpl implements PharmacyService {

	private static final Logger logger = LoggerFactory.getLogger(PharmacyServiceImpl.class);

    @Autowired
    private PharmacyRepo pharmacyRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PharmacyDTO createPharmacy(PharmacyCreateDTO pharmacyCreateDTO) {
        logger.info("Creating Pharmacy with details: {}", pharmacyCreateDTO);
        Pharmacy pharmacy = modelMapper.map(pharmacyCreateDTO, Pharmacy.class);
        Pharmacy savedPharmacy = pharmacyRepository.save(pharmacy);
        PharmacyDTO pharmacyDTO = modelMapper.map(savedPharmacy, PharmacyDTO.class);
        logger.info("Created Pharmacy DTO: {}", pharmacyDTO);
        return pharmacyDTO;
    }

    @Override
    public List<PharmacyCreateDTO> getAllPharmacies() {
        List<Pharmacy> pharmacies = pharmacyRepository.findAll();
        return pharmacies.stream()
                .map(pharmacy -> modelMapper.map(pharmacy, PharmacyCreateDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public PharmacyDTO getPharmacyById(Long id) {
        Pharmacy pharmacy = pharmacyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pharmacy not found"));
        PharmacyDTO pharmacyDTO = modelMapper.map(pharmacy, PharmacyDTO.class);
        logger.info("Retrieved Pharmacy DTO by ID {}: {}", id, pharmacyDTO);
        return pharmacyDTO;
    }

    @Override
    public PharmacyDTO getPharmacyDetails(Long id) {
        return getPharmacyById(id);
    }
    @Override
    public PharmacyDTO authenticatePharma(String email, String password) {
        Pharmacy pharma = pharmacyRepository.findByEmail(email);
        if (pharma != null) {
            logger.info("Pharmacy found with email: {}", email);
            logger.info("Stored password: {}", pharma.getPassword());
            logger.info("Provided password: {}", password);
            
            if (password.equals(pharma.getPassword())) {
                return new PharmacyDTO(pharma.getId(), pharma.getPharmacyName(), pharma.getAddress(), pharma.getContact(), pharma.getOperatingHoursFrom(), pharma.getOperatingHoursTo(), pharma.getEmail(), pharma.getPincode());
            } else {
                logger.info("Password mismatch for email: {}", email);
            }
        } else {
            logger.info("No pharmacy found with email: {}", email);
        }
        return null;
    }
    @Override
    public List<PharmacyDTO> getPharmaciesByPincode(String pincode) {
        logger.info("Fetching pharmacies with pincode: {}", pincode);
        List<Pharmacy> pharmacies = pharmacyRepository.findByPincode(pincode);
        List<PharmacyDTO> pharmacyDTOs = pharmacies.stream()
                .map(pharmacy -> modelMapper.map(pharmacy, PharmacyDTO.class))
                .collect(Collectors.toList());
        logger.info("Found {} pharmacies for pincode {}: {}", pharmacyDTOs.size(), pincode, pharmacyDTOs);
        return pharmacyDTOs;
    }

}
