package edu.miu.eshop.service.implementation;

import edu.miu.eshop.DTO.ShoppingCartRequest;
import edu.miu.eshop.model.Customer;
import edu.miu.eshop.model.Product;
import edu.miu.eshop.model.ShoppingCart;
import edu.miu.eshop.model.ShoppingCartItem;
import edu.miu.eshop.repository.CustomerRepository;
import edu.miu.eshop.repository.ProductRepository;
import edu.miu.eshop.repository.ShoppingCartItemRepository;
import edu.miu.eshop.repository.ShoppingCartRepository;
import edu.miu.eshop.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final CustomerRepository customerRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final ShoppingCartItemRepository shoppingCartItemRepository;
    private final ProductRepository productRepository;

    @Autowired
    public ShoppingCartServiceImpl(CustomerRepository customerRepository, ShoppingCartRepository shoppingCartRepository, ShoppingCartItemRepository shoppingCartItemRepository, ProductRepository productRepository) {
        this.customerRepository = customerRepository;
        this.shoppingCartRepository = shoppingCartRepository;
        this.shoppingCartItemRepository = shoppingCartItemRepository;
        this.productRepository = productRepository;
    }

    @Override
    public ShoppingCartItem addCartItem(ShoppingCartRequest request) {
        if(request.getShoppingCartId() == null) {
            Customer customer = this.customerRepository.findById(request.getUserId()).orElse(null);
            Product product = this.productRepository.findById(request.getProductId()).orElse(null);
            ShoppingCart shoppingCart = this.shoppingCartRepository.save(new ShoppingCart(null, customer, LocalDate.now(), true));

            return this.shoppingCartItemRepository.save(new ShoppingCartItem(null, shoppingCart, product, 1));
        }
        else {
            ShoppingCartItem shoppingCartItem = this.shoppingCartItemRepository.findAll().stream().filter(x -> x.getShoppingCart().getShoppingCartId() == request.getShoppingCartId() && x.getProduct().getId() == request.getProductId()).findFirst().orElse(null);
            if(shoppingCartItem == null) {
                Product product = this.productRepository.findById(request.getProductId()).orElse(null);
                ShoppingCart shoppingCart = this.shoppingCartRepository.getById(request.getShoppingCartId());
                return this.shoppingCartItemRepository.save(new ShoppingCartItem(null, shoppingCart, product, 1));
            }
            else {
                shoppingCartItem.setQuantity(shoppingCartItem.getQuantity() + 1);
                return this.shoppingCartItemRepository.save(shoppingCartItem);
            }
        }
    }

    @Override
    public void updateQuantity(Long shoppingCartId, Long productId, int isAdd) {
        ShoppingCartItem shoppingCartItem = this.shoppingCartItemRepository.findAll().stream().filter(x -> x.getShoppingCart().getShoppingCartId() == shoppingCartId && x.getProduct().getId() == productId).findFirst().orElse(null);
        if(shoppingCartItem != null) {
            if (isAdd == 1)
                shoppingCartItem.setQuantity(shoppingCartItem.getQuantity() + 1);
            else
                shoppingCartItem.setQuantity(shoppingCartItem.getQuantity() - 1);

            this.shoppingCartItemRepository.save(shoppingCartItem);

            if(shoppingCartItem.getQuantity() == 0) {
                this.shoppingCartItemRepository.delete(shoppingCartItem);
                if (!this.shoppingCartItemRepository.findAll().stream()
                        .filter(x -> x.getShoppingCart().getShoppingCartId() == shoppingCartId)
                        .findAny().isPresent()) {
                    ShoppingCart shoppingCart = this.shoppingCartRepository.getById(shoppingCartId);
                    this.shoppingCartRepository.delete(shoppingCart);
                }
            }


        }
    }

    @Override
    public List<ShoppingCartItem> getShoppingCartDetails(Long shoppingCartId) {
        return this.shoppingCartItemRepository.findAll().stream().filter(x -> x.getShoppingCart().getShoppingCartId() == shoppingCartId).collect(Collectors.toList());
    }

    @Override
    public Long getActiveShoppingCartId(Long userId) {
        ShoppingCart shoppingCart = this.shoppingCartRepository.findAll().stream().filter(x -> x.getCustomer().getCustomerId() == userId).findFirst().orElseGet(() -> null);

        if (shoppingCart != null) {
            return shoppingCart.getShoppingCartId();
        }

        return null;
    }

    @Override
    public Integer getShoppingCartItemsNumber(Long shoppingCartId) {
        List<ShoppingCartItem> shoppingCartItems = this.shoppingCartItemRepository.findAll().stream().filter(x -> x.getShoppingCart().getShoppingCartId() == shoppingCartId).collect(Collectors.toList());

        Integer quantity = 0;
        for (var item : shoppingCartItems) {
            quantity += item.getQuantity();
        }
        if (shoppingCartItems.size() > 0) return quantity;

        return 0;
    }
}
