package edu.miu.eshop.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "shopping_cart")
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long shoppingCartId;

    @OneToOne
    @JoinColumn(name = "customerId")
    private Customer customer;

    private LocalDate dateAdded;

    private boolean isActive;

    public ShoppingCart() {
    }

    public ShoppingCart(Long shoppingCartId, Customer customer, LocalDate dateAdded, boolean isActive) {
        this.shoppingCartId = shoppingCartId;
        this.customer = customer;
        this.dateAdded = dateAdded;
        this.isActive = isActive;
    }

    public Long getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(Long shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDate getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDate dateAdded) {
        this.dateAdded = dateAdded;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
