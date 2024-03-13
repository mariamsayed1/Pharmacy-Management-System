package com.example.aswe.demo.Controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.aswe.demo.Models.Category;
import com.example.aswe.demo.Repositories.CategoryRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/")

public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

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
    public ModelAndView SaveCategory(@ModelAttribute Category category) {
        this.categoryRepository.save(category);
        return new ModelAndView("addCategory.html");
    }
}
