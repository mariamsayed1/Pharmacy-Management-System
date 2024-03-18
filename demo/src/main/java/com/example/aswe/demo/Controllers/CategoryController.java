package com.example.aswe.demo.Controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.aswe.demo.Models.Category;
import com.example.aswe.demo.Models.Product;
import com.example.aswe.demo.Repositories.CategoryRepository;
import com.example.aswe.demo.Repositories.ProductRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


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

    @GetMapping("/category/{id}")
    public ModelAndView getCategory(@PathVariable("id") int id, @RequestParam(defaultValue = "1") int page) {
        ModelAndView mav = new ModelAndView("category.html");
        Category category = this.categoryRepository.findById(id);
        if (category == null) {
            mav.addObject("errorMessage", "Category not found");
            return mav;
        }
        Page<Product> productsPage = this.productRepository.findAllByCategoryId(id, PageRequest.of(page-1, 3));

        mav.addObject("category", category);
        mav.addObject("products", productsPage.getContent());
        mav.addObject("currentPage", page); 
        mav.addObject("totalPages", productsPage.getTotalPages());
        return mav;
    }

    @GetMapping("/productDetails/{id}")
    public ModelAndView getProductInfo(@PathVariable("id") int id) {
        ModelAndView mav = new ModelAndView("productDetails.html");
        Product product = this.productRepository.findById(id);
        mav.addObject("product", product);
        return mav;
    }
}
