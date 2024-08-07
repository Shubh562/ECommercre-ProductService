package com.ecommerce.productservice.services;

import com.ecommerce.productservice.dtos.FakeProductStoreDto;
import com.ecommerce.productservice.models.Product;
import com.ecommerce.productservice.transformers.ProductTransformer;
import com.ecommerce.productservice.utils.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
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
        FakeProductStoreDto[] fakeProductStoreDtos = restTemplate.getForObject("https://fakestoreapi.com/products", FakeProductStoreDto[].class);
        List<Product> products = new ArrayList<>();
        for(FakeProductStoreDto fakeProductStoreDto: fakeProductStoreDtos) {
            products.add(productTransformer.convertFakeProductDtoToProduct(fakeProductStoreDto));
        }
        return products;
    }

    @Override
    public void deleteProduct(Long id) {
        restTemplate.delete("https://fakestoreapi.com/products/" + id);
    }

    @Override
    public Product createProduct(Product product) {
        FakeProductStoreDto fakeProductStoreDto = productTransformer.convertProductToFakeProductDto(product);
        FakeProductStoreDto response = restTemplate.postForObject("https://fakestoreapi.com/products", fakeProductStoreDto, FakeProductStoreDto.class);
        return productTransformer.convertFakeProductDtoToProduct(response);
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
       FakeProductStoreDto newProduct= productTransformer.convertProductToFakeProductDto(product);
       restTemplate.put("https://fakestoreapi.com/products/" + id, newProduct);
       return product;
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        FakeProductStoreDto newProduct= productTransformer.convertProductToFakeProductDto(product);
        FakeProductStoreDto updatedProduct= restTemplate.patchForObject("https://fakestoreapi.com/products/" + id, newProduct, FakeProductStoreDto.class);
        return productTransformer.convertFakeProductDtoToProduct(updatedProduct);
    }
}
