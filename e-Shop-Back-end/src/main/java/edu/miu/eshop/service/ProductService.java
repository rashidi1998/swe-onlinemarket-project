package edu.miu.eshop.service;

import edu.miu.eshop.DTO.ProductsRequest;
import edu.miu.eshop.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAll();

    List<Product> getBySubCategoryId(ProductsRequest request);
}
