package com.ecommerce.productservice.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    private long id;

    private String title;

    private Double price;

    private Category category;;

    private String description;
}
