package com.example.aswe.demo.Controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.aswe.demo.Models.Category;
import com.example.aswe.demo.Models.Product;
import com.example.aswe.demo.Repositories.CategoryRepository;
import com.example.aswe.demo.Repositories.ProductRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/")

public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("")
    public ModelAndView getAllCategories() {
        ModelAndView mav = new ModelAndView("index.html");
        List<Category> categories = this.categoryRepository.findAll();
        mav.addObject("categories", categories);
        return mav;
    }

    // @GetMapping("/admin")
    // public ModelAndView index() {         
    //     return new ModelAndView("admin.html");
    //  }


    @GetMapping("/category/{id}")
    public ModelAndView getCategory(@PathVariable("id") int id) {
        ModelAndView mav = new ModelAndView("category.html");
        Category category = this.categoryRepository.findById(id);
        List<Product> products = this.productRepository.findAllByCategoryId(id);
        mav.addObject("category", category);
        mav.addObject("products", products);
        return mav;
    }

    @GetMapping("/productDetails/{id}")
    public ModelAndView getProduct(@PathVariable("id") int id) {
        ModelAndView mav = new ModelAndView("productDetails.html");
        Product product = this.productRepository.findById(id);
        mav.addObject("product", product);
        return mav;
    }
}
