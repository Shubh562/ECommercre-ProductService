package com.ecommerce.productservice.services;

import com.ecommerce.productservice.models.Product;

import java.util.List;

public interface ProductService {
    Product getProductById(long id);
    List<Product> getProducts();
    void deleteProduct(Long id);
    Product createProduct(Product product);
    Product replaceProduct(Long id, Product product);
    Product updateProduct(Long id, Product product);
}
