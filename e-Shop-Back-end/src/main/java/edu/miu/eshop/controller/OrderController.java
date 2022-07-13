package edu.miu.eshop.controller;

import edu.miu.eshop.DTO.OrderRequest;
import edu.miu.eshop.model.Order;
import edu.miu.eshop.service.implementation.OrderServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = {"api", "api/order"})
public class OrderController {

    private final OrderServiceImpl orderService;

    public OrderController(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    @GetMapping(value = "/")
    public List<Order> getOrders(Long customerId) {
        return this.orderService.getOrders(customerId);
    }

    @PostMapping(value = "/placeOrder")
    public Order placeOrder(@RequestBody OrderRequest request) {
        return this.orderService.addOrder(request);
    }
}
