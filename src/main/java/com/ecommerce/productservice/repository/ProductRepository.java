package com.ecommerce.productservice.repository;

import com.ecommerce.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
    @Override
    Optional<Product> findById(Long id);

    @Override
    void delete(Product product);

    Product save(Product product);
}
