package com.example.PharmaFinderAPI.service.impl;

import com.example.PharmaFinderAPI.dto.MedicineDTO;
import com.example.PharmaFinderAPI.dto.MedicineQuantityDTO;
import com.example.PharmaFinderAPI.dto.StockDetailsDTO;
import com.example.PharmaFinderAPI.dto.StocksDTO;
import com.example.PharmaFinderAPI.entity.Medicine;
import com.example.PharmaFinderAPI.entity.Stocks;
import com.example.PharmaFinderAPI.repository.StocksRepo;
import com.example.PharmaFinderAPI.service.StocksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockServiceImpl implements StocksService {

    @Autowired
    private StocksRepo stocksRepository;

    @Override
    public List<StocksDTO> getStocksByPharmacyId(Long pharmacyId) {
        return stocksRepository.findByPharmacyId(pharmacyId).stream()
            .map(stock -> {
                StocksDTO dto = new StocksDTO();
                dto.setId(stock.getId());
                dto.setPharmacyId(stock.getPharmacy().getId());
                dto.setMedicineId(stock.getMedicine().getId());
                dto.setQuantity(stock.getQuantity());
                return dto;
            }).collect(Collectors.toList());
    }
    @Override
    public List<StockDetailsDTO> getStocks(Long pharmacyId) {
        List<Stocks> stocksList = stocksRepository.findByPharmacyId(pharmacyId);

        // Create a list to hold the aggregated quantities of each medicine
        List<MedicineQuantityDTO> medicineQuantityList = new ArrayList<>();

        for (Stocks stock : stocksList) {
            Medicine medicine = stock.getMedicine();
            // Check if the medicine is already in the list
            MedicineQuantityDTO existingMedicineQuantity = medicineQuantityList.stream()
                .filter(mq -> mq.getMedicine().equals(medicine))
                .findFirst()
                .orElse(null);

            if (existingMedicineQuantity != null) {
                // If the medicine already exists, update its quantity
                existingMedicineQuantity.setQuantity(existingMedicineQuantity.getQuantity() + stock.getQuantity());
            } else {
                // If the medicine does not exist, add it to the list
                medicineQuantityList.add(new MedicineQuantityDTO(medicine, stock.getQuantity()));
            }
        }

        // Return the StockDetailsDTO with the aggregated list of MedicineQuantity
        return List.of(new StockDetailsDTO(null, medicineQuantityList));
    }
    
}
