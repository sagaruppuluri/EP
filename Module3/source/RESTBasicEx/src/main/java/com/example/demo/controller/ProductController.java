package com.example.demo.controller;

import com.example.demo.model.Product;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller to demonstrate API Versioning through URL
 * For new version of the API the version number will be changed to v2 e.g. /api/v2/products.
 */

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    List<Product> products = new ArrayList<>();

    @PostConstruct
    public void init() {
        products.add(new Product(1L, "a", 100));
        products.add(new Product(2L, "b", 200));
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return products;
    }
}
