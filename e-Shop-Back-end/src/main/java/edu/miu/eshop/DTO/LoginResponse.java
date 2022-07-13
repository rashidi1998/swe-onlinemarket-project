package edu.miu.eshop.DTO;

import edu.miu.eshop.model.Customer;

public class LoginResponse {
    private Customer customer;
    private int bagItemsQuantity;
    private Long shoppingCartId;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getBagItemsQuantity() {
        return bagItemsQuantity;
    }

    public void setBagItemsQuantity(int bagItemsQuantity) {
        this.bagItemsQuantity = bagItemsQuantity;
    }

    public Long getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(Long shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }
}
