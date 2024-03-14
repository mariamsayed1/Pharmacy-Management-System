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
    public ModelAndView getCategories() {
        ModelAndView mav = new ModelAndView("index.html");
        List<Category> categories = this.categoryRepository.findAll();
        mav.addObject("categories", categories);
        return mav;
    }
    @GetMapping("addCategory")
    public ModelAndView addCategories() {
        ModelAndView mav = new ModelAndView("addCategory.html");
        Category newCategory = new Category();
        mav.addObject("category", newCategory);
        return mav;
    }

    @PostMapping("addCategory")
    public ModelAndView saveCategory(@ModelAttribute Category category) {
        this.categoryRepository.save(category);
        return new ModelAndView("redirect:/addCategory");
    }

    // @GetMapping("/admin")
    // public ModelAndView index() {         
    //     return new ModelAndView("admin.html");
    //  }

    @GetMapping("addProduct")
    public ModelAndView addProduct() {
        ModelAndView mav = new ModelAndView("addProduct.html");
        List<Category> allCategories = this.categoryRepository.findAll();
        mav.addObject("allCategories", allCategories);
        Product newProduct = new Product();
        mav.addObject("product", newProduct);
        return mav;
    }

    @PostMapping("addProduct")
    public ModelAndView saveProduct(@ModelAttribute Product product) {
        this.productRepository.save(product);
        return new ModelAndView("redirect:/addProduct");
    }

    @GetMapping("/category/{id}")
    public ModelAndView getCategory(@PathVariable("id") int id) {
        ModelAndView mav = new ModelAndView("category.html");
        Category category = this.categoryRepository.findById(id);
        List<Product> products = this.productRepository.findAllByCategoryId(id);
        mav.addObject("category", category);
        mav.addObject("products", products);
        return mav;
    }
}
