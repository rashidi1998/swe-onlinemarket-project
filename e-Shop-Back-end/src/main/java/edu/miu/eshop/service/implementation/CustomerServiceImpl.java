package edu.miu.eshop.service.implementation;

import edu.miu.eshop.DTO.LoginResponse;
import edu.miu.eshop.model.Customer;
import edu.miu.eshop.repository.CustomerRepository;
import edu.miu.eshop.service.CustomerService;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository CustomerRepository;
    private final ShoppingCartServiceImpl shoppingCartService;

    public CustomerServiceImpl(CustomerRepository CustomerRepository, ShoppingCartServiceImpl shoppingCartService) {
        this.CustomerRepository = CustomerRepository;
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    public LoginResponse login(String email, String password) {
        Customer customer = CustomerRepository
                .findAll()
                .stream()
                .filter(c -> c.getEmail().equals(email) && c.getPassword().equals(password))
                .findFirst()
                .orElseGet(() -> null);

        if (customer != null) {
            var loginRes = new LoginResponse();
            loginRes.setCustomer(customer);
            loginRes.setShoppingCartId(shoppingCartService.getActiveShoppingCartId(customer.getCustomerId()));
            loginRes.setBagItemsQuantity(shoppingCartService.getShoppingCartItemsNumber(loginRes.getShoppingCartId()));
            return loginRes;
        }

        return null;
    }

    @Override
    public Customer signup(Customer Customer) {
        if (CustomerRepository.findAll().stream().anyMatch(u -> u.getEmail().equals(Customer.getEmail()))) {
            return null;
        }
        return CustomerRepository.save(Customer);

    }
}
