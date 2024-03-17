package com.example.aswe.demo.Controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.aswe.demo.Models.Product;
import com.example.aswe.demo.Repositories.ProductRepository;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/search")
    public List<Product> searchProducts(@RequestParam String term) {
        return productRepository.findByNameStartingWithIgnoreCase(term);
    }
}