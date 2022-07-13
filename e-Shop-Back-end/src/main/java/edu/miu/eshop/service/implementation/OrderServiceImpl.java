package edu.miu.eshop.service.implementation;

import edu.miu.eshop.DTO.OrderRequest;
import edu.miu.eshop.model.*;
import edu.miu.eshop.repository.*;
import edu.miu.eshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final ShoppingCartItemRepository shoppingCartItemRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, CustomerRepository customerRepository, ShoppingCartRepository shoppingCartRepository, ShoppingCartItemRepository shoppingCartItemRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.shoppingCartItemRepository = shoppingCartItemRepository;
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> getOrders(Long customerId) {
        return this.orderRepository.findById(customerId).stream().toList();
    }

    @Override
    public Order addOrder(OrderRequest request) {

        Customer customer = this.customerRepository.findById(request.getUserId()).orElse(null);
        ShoppingCart shoppingCart = this.shoppingCartRepository.findById(request.getShoppingCartId()).orElse(null);
        List<ShoppingCartItem> shoppingCartItems = this.shoppingCartItemRepository.findAll().stream().filter(x -> x.getShoppingCart().getShoppingCartId() == shoppingCart.getShoppingCartId()).toList();
        Double Total = 0.0;

        for (var item : shoppingCartItems) {
            Total += item.getProduct().getPrice();
        }

        Order order = new Order(null, customer, shoppingCart, Total, LocalDate.now());
        return this.orderRepository.save(order);
    }
}
