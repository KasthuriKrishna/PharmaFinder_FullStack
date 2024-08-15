package com.example.PharmaFinderAPI.service;
import com.example.PharmaFinderAPI.dto.MedicineDTO;
import com.example.PharmaFinderAPI.dto.OrderHistoryDTO;
import com.example.PharmaFinderAPI.dto.UserDTO;
import com.example.PharmaFinderAPI.entity.Medicine;
import com.example.PharmaFinderAPI.entity.OrderHistory;
import com.example.PharmaFinderAPI.repository.OrderHistoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderHistoryService {

    @Autowired
    private OrderHistoryRepo orderHistoryRepository;

    public OrderHistory createOrderHistory(OrderHistory orderHistory) {
        return orderHistoryRepository.save(orderHistory);
    }

    public List<OrderHistory> getAllOrderHistories() {
        return orderHistoryRepository.findAll();
    }

    public Optional<OrderHistory> getOrderHistoryById(Long id) {
        return orderHistoryRepository.findById(id);
    }
    
    //displays the particular user order details in a date for a particular pharmacy
    public Map<String, Map<String, Object>> getOrdersGroupedByDateAndUser(Long pharmacyId) {
        // Fetch orders based on pharmacy ID
        List<OrderHistory> orders = orderHistoryRepository.findOrdersByPharmacyId(pharmacyId);

        // Group by date and then by user ID
        Map<String, Map<Long, List<OrderHistory>>> groupedByDateAndUser = orders.stream()
                .collect(Collectors.groupingBy(
                        order -> order.getOrderDate().toLocalDate().toString(), // Group by date
                        Collectors.groupingBy(
                                order -> order.getUser().getId() // Group by user ID
                        )
                ));

        // Transform to desired format
        Map<String, Map<String, Object>> result = new LinkedHashMap<>();
        for (Map.Entry<String, Map<Long, List<OrderHistory>>> dateEntry : groupedByDateAndUser.entrySet()) {
            String date = dateEntry.getKey();
            Map<Long, List<OrderHistory>> userOrders = dateEntry.getValue();

            Map<String, Object> dateResult = new LinkedHashMap<>();
            for (Map.Entry<Long, List<OrderHistory>> userEntry : userOrders.entrySet()) {
                Long userId = userEntry.getKey();
                List<OrderHistory> userOrderList = userEntry.getValue();

                UserDTO userDTO = new UserDTO(
                        userOrderList.get(0).getUser().getId(),
                        userOrderList.get(0).getUser().getUsername(),
                        userOrderList.get(0).getUser().getEmail(),
                        userOrderList.get(0).getUser().getDob(),
                        userOrderList.get(0).getUser().getMobileNumber(),
                        userOrderList.get(0).getUser().getAddress(),
                        userOrderList.get(0).getUser().getPincode()
                );

                List<Medicine> medicines = userOrderList.stream()
                        .map(order -> new Medicine(
                                order.getMedicine().getId(),
                                order.getMedicine().getName(),
                                order.getMedicine().getPurpose(),
                                order.getMedicine().getCategory(),
                                order.getMedicine().getBrand(),
                                order.getMedicine().getCost(),
                                order.getMedicine().getForm()
                        ))
                        .collect(Collectors.toList());

                Map<String, Object> userResult = new LinkedHashMap<>();
                userResult.put("user", userDTO);
                userResult.put("medicines", medicines);

                dateResult.put("user_" + userId, userResult);
            }
            result.put(date, dateResult);
        }

        return result;
    }
}
