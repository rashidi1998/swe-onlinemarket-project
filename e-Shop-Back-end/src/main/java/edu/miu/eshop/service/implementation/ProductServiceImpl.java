package edu.miu.eshop.service.implementation;

import edu.miu.eshop.DTO.ProductsRequest;
import edu.miu.eshop.model.Product;
import edu.miu.eshop.repository.ProductRepository;
import edu.miu.eshop.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getBySubCategoryId(ProductsRequest request) {
        return productRepository.findAll()
                .stream()
                .filter(product -> product.getSubCategory().getId() == request.getSubCategoryId())
                .collect(Collectors.toList());
    }
}
