package edu.miu.eshop.controller;

import edu.miu.eshop.DTO.ShoppingCartRequest;
import edu.miu.eshop.DTO.UpdateQuantityRequest;
import edu.miu.eshop.model.ShoppingCartItem;
import edu.miu.eshop.service.implementation.ShoppingCartServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = {"api", "api/shoppingCart"})
public class ShoppingCartController {
    private final ShoppingCartServiceImpl shoppingCartService;

    public ShoppingCartController(ShoppingCartServiceImpl shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @PostMapping(value = "/addCartItem")
    public ShoppingCartItem addCartItem(@RequestBody ShoppingCartRequest request) {
        return this.shoppingCartService.addCartItem(request);
    }

    @PostMapping(value = "/updateQuantity")
    public void updateQuantity(@RequestBody UpdateQuantityRequest request) {
        this.shoppingCartService.updateQuantity(request.getShoppingCartId(), request.getProductId(), request.getIsAdd());
    }

    @GetMapping(value = "/getShoppingCartDetails/{id}")
    public List<ShoppingCartItem> getShoppingCartDetails(@PathVariable Long id) {
        return this.shoppingCartService.getShoppingCartDetails(id);
    }
}
