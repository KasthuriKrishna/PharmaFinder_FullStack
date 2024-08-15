package com.example.PharmaFinderAPI.controller;
import com.example.PharmaFinderAPI.dto.StockDetailsDTO;
import com.example.PharmaFinderAPI.dto.StocksDTO;
import com.example.PharmaFinderAPI.service.StocksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stocks")
public class StocksController {

    @Autowired
    private StocksService stocksService;
    
    //display the stocks in particular pharmacy
    @GetMapping("/{pharmacyId}")
    public ResponseEntity<List<StocksDTO>> getStocksByPharmacyId(@PathVariable Long pharmacyId) {
        return ResponseEntity.ok(stocksService.getStocksByPharmacyId(pharmacyId));
    }
    
    //displays stocks
    @GetMapping("/groups/{pharmacyId}")
    public ResponseEntity<List<StockDetailsDTO>> getStocks(@PathVariable Long pharmacyId) {
        return ResponseEntity.ok(stocksService.getStocks(pharmacyId));
    }
}
