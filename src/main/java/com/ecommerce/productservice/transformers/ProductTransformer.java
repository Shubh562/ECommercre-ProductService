package com.ecommerce.productservice.transformers;

import com.ecommerce.productservice.dtos.FakeProductStoreDto;
import com.ecommerce.productservice.models.Category;
import com.ecommerce.productservice.models.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductTransformer {

    public Product convertFakeProductDtoToProduct(FakeProductStoreDto dto) {
        if(dto == null) {
            return null;
        }
        Product product = new Product();
        product.setId(dto.getId());
        product.setTitle(dto.getTitle());
        product.setPrice(dto.getPrice());
        product.setDescription(dto.getDescription());
        Category category = new Category();
        category.setId(0L);
        category.setTitle(dto.getCategory());
        product.setCategory(category);
        return product;
    }
}
