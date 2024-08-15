package com.example.PharmaFinderAPI.service;
import com.example.PharmaFinderAPI.dto.StockDetailsDTO;
import com.example.PharmaFinderAPI.dto.StocksDTO;

import java.util.List;

public interface StocksService {
    List<StocksDTO> getStocksByPharmacyId(Long pharmacyId);
    List<StockDetailsDTO> getStocks(Long pharmacyId);
}
