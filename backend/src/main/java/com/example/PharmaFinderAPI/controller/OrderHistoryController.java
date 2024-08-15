package com.example.PharmaFinderAPI.controller;
import com.example.PharmaFinderAPI.entity.OrderHistory;
import com.example.PharmaFinderAPI.entity.User;
import com.example.PharmaFinderAPI.entity.Pharmacy;
import com.example.PharmaFinderAPI.dto.OrderHistoryDTO;
import com.example.PharmaFinderAPI.entity.Medicine;
import com.example.PharmaFinderAPI.service.OrderHistoryService;
import com.example.PharmaFinderAPI.service.UserService;
import com.example.PharmaFinderAPI.service.PharmacyService;
import com.example.PharmaFinderAPI.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/order-histories")
public class OrderHistoryController {

    @Autowired
    private OrderHistoryService orderHistoryService;

    @Autowired
    private UserService userService;

    @Autowired
    private PharmacyService pharmacyService;

    @Autowired
    private MedicineService medicineService;
    
    //insert the order details
    @PostMapping
    public ResponseEntity<OrderHistory> createOrderHistory(@RequestBody OrderHistory orderHistory) {
        OrderHistory createdOrderHistory = orderHistoryService.createOrderHistory(orderHistory);
        return new ResponseEntity<>(createdOrderHistory, HttpStatus.CREATED);
    }
    
    //display all the orders
    @GetMapping
    public ResponseEntity<List<OrderHistory>> getAllOrderHistories() {
        List<OrderHistory> orderHistories = orderHistoryService.getAllOrderHistories();
        return new ResponseEntity<>(orderHistories, HttpStatus.OK);
    }
    
    //fetch the details of particular order
    @GetMapping("/{id}")
    public ResponseEntity<OrderHistory> getOrderHistoryById(@PathVariable Long id) {
        Optional<OrderHistory> orderHistory = orderHistoryService.getOrderHistoryById(id);
        return orderHistory.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    //display the orders made by different users to a particular pharmacy and grouping it based on the dates
    @GetMapping("/grouped-by-date-and-user/{pharmacyId}")
    public ResponseEntity<Map<String, Map<String, Object>>> getOrdersGroupedByDateAndUser(
            @PathVariable Long pharmacyId) {
        Map<String, Map<String, Object>> result = orderHistoryService.getOrdersGroupedByDateAndUser(pharmacyId);
        return ResponseEntity.ok(result);
    }
}
