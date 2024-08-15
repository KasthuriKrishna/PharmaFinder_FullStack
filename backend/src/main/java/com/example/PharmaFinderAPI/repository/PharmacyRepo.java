package com.example.PharmaFinderAPI.repository;
import com.example.PharmaFinderAPI.entity.Pharmacy;
import com.example.PharmaFinderAPI.entity.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PharmacyRepo extends JpaRepository<Pharmacy, Long> {
	
	//returns pharmacies in a particular location with the desired medicine
    @Query("SELECT p FROM Pharmacy p WHERE p.pincode = :pincode AND EXISTS " +
           "(SELECT s FROM Stocks s WHERE s.pharmacy.id = p.id AND s.medicine.id = :medicineId AND s.quantity > 0)")
    List<Pharmacy> findPharmaciesByPincodeAndMedicine(@Param("pincode") String pincode, @Param("medicineId") Long medicineId);
    
    //fetch pharmacy communication details
    Pharmacy findByEmail(String email); 
    
    //List the pharmacies in a particular pincode
    List<Pharmacy> findByPincode(String pincode);
}