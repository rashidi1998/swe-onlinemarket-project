package edu.miu.eshop.service;

import edu.miu.eshop.DTO.ShoppingCartRequest;
import edu.miu.eshop.model.ShoppingCart;
import edu.miu.eshop.model.ShoppingCartItem;

import java.util.List;

public interface ShoppingCartService {
    ShoppingCartItem addCartItem(ShoppingCartRequest request);

    void updateQuantity(Long shoppingCartId, Long productId, int isAdd);

    List<ShoppingCartItem> getShoppingCartDetails(Long shoppingCartId);

    Long getActiveShoppingCartId(Long userId);

    Integer getShoppingCartItemsNumber(Long userId);
}
