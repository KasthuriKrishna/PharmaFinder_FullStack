package com.example.PharmaFinderAPI.repository;
import com.example.PharmaFinderAPI.entity.Dosage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DosageRepo extends JpaRepository<Dosage, Long> {
	//Returns the dosages of specific medicine
    List<Dosage> findByMedicineId(Long medicineId);
    Optional<Dosage> findByMedicineIdAndDosage(Long medicineId, String dosage);
}