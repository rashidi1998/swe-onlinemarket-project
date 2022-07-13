package edu.miu.eshop.service;

import edu.miu.eshop.DTO.LoginResponse;
import edu.miu.eshop.model.Customer;

public interface CustomerService {
    LoginResponse login(String email, String password);
    Customer signup(Customer user);
}
