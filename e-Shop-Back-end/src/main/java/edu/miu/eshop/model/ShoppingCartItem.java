package edu.miu.eshop.model;

import javax.persistence.*;

@Entity
@Table(name = "shopping_cart_item")
public class ShoppingCartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "shoppingCartId")
    private ShoppingCart shoppingCart;

    @OneToOne
    @JoinColumn(name = "productId")
    private Product product;

    private Integer quantity;

    public ShoppingCartItem() {
    }

    public ShoppingCartItem(Long id, ShoppingCart shoppingCart, Product product, Integer quantity) {
        this.id = id;
        this.shoppingCart = shoppingCart;
        this.product = product;
        this.quantity = quantity;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
