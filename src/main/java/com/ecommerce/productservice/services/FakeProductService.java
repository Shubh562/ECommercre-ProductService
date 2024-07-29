package com.ecommerce.productservice.services;

import com.ecommerce.productservice.dtos.FakeProductStoreDto;
import com.ecommerce.productservice.models.Product;
import com.ecommerce.productservice.transformers.ProductTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FakeProductService implements ProductService{

    private RestTemplate restTemplate;

    private ProductTransformer productTransformer;

    @Autowired
    public FakeProductService(RestTemplate restTemplate, ProductTransformer productTransformer) {
        this.restTemplate = restTemplate;
        this.productTransformer = productTransformer;
    }

    @Override
    public Product getProductById(long id) {
        FakeProductStoreDto fakeProductStoreDto = restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeProductStoreDto.class);
        return productTransformer.convertFakeProductDtoToProduct(fakeProductStoreDto);
    }

    @Override
    public List<Product> getProducts() {
        return List.of();
    }

    @Override
    public void deleteProduct(Long id) {

    }

    @Override
    public Product createProduct(Product product) {
        FakeProductStoreDto fakeProductStoreDto = productTransformer.convertProductToFakeProductDto(product);
        FakeProductStoreDto response = restTemplate.postForObject("https://fakestoreapi.com/products", fakeProductStoreDto, FakeProductStoreDto.class);
        return productTransformer.convertFakeProductDtoToProduct(response);
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        return null;
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        return null;
    }
}
