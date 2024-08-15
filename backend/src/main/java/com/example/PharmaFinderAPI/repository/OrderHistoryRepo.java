package com.example.PharmaFinderAPI.repository;
import com.example.PharmaFinderAPI.entity.OrderHistory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderHistoryRepo extends JpaRepository<OrderHistory, Long> {
	
	//returns the orders posted to particular pharmacy
	@Query("SELECT o FROM OrderHistory o WHERE o.pharmacy.id = :pharmacyId")
    List<OrderHistory> findOrdersByPharmacyId(@Param("pharmacyId") Long pharmacyId);
}
