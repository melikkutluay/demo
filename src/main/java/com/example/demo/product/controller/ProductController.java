package com.example.demo.product.controller;

import com.example.demo.product.exception.ProductNotFoundException;
import com.example.demo.product.model.Product;
import com.example.demo.product.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/demo") // This means URL's start with /demo (after Application path)

public class ProductController {
    private ProductRepository productRepository;
    //Product null gelmemesi için
    ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping(path = "/product")
    List<Product> all() {
        return productRepository.findAll();
    }


    @GetMapping("product/{id}")
    Product one(@PathVariable Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

    @PostMapping("/product")
    Product newProduct(@RequestBody Product reqBody) {
        return productRepository.save(reqBody);
    }
}
