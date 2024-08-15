package com.example.PharmaFinderAPI.repository;
import com.example.PharmaFinderAPI.entity.Replies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepliesRepo extends JpaRepository<Replies, Long> {
    List<Replies> findByUserIdAndPharmacyId(Long userId, Long pharmacyId);
    List<Replies> findByUserId(Long userId);
}
