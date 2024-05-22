package com.example.aswe.demo.Controllers;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.example.aswe.demo.Models.Category;
import com.example.aswe.demo.Models.Pharmacist;
import com.example.aswe.demo.Models.Product;
import com.example.aswe.demo.Repositories.CategoryRepository;
import com.example.aswe.demo.Repositories.PharmacistRepository;
import com.example.aswe.demo.Repositories.ProductRepository;


import jakarta.servlet.http.HttpSession;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/Pharmacist")
public class PharmacistController {
    @Autowired
    private CategoryRepository categoryRepository;
     @Autowired
    private PharmacistRepository pharmacistRepository;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("")
    public ModelAndView pharmacistIndex() {         
        return new ModelAndView("pharmacist.html");
    }

    @GetMapping("/login1")
    public ModelAndView login1() {
        return new ModelAndView("login1.html");
    }

    @PostMapping("/login1")
    public ModelAndView loginProcess(@RequestParam("username") String username,
                                     @RequestParam("password") String password,
                                     HttpSession session) {
        ModelAndView mav = new ModelAndView("login1.html");

        if (username == null || password == null) {
            mav.addObject("loginError", "Please provide both username and password");
            return mav;
        }

        Pharmacist dbPharmacist = pharmacistRepository.findByUsername(username);
        if (dbPharmacist == null) {
            // mav.addObject("loginError", "Username not found");
            // return mav;
            mav.addObject("loginError", "Username not found");
            mav.addObject("loginErrorField", "username");
            return mav;
        }

        boolean isPasswordMatched = BCrypt.checkpw(password, dbPharmacist.getPassword());
        if (!isPasswordMatched) {
            // mav.addObject("loginError", "Incorrect password");
            // return mav;
            mav.addObject("loginError", "Incorrect password");
            mav.addObject("loginErrorField", "password");
            return mav;
        }

        
        return new ModelAndView("redirect:/Pharmacist");
    }
    @GetMapping("categories")
    public ModelAndView getAllCategories() {
        ModelAndView mav = new ModelAndView("listCategoryPh.html");
        List<Category> categories = this.categoryRepository.findAll();
        mav.addObject("categories", categories);
        return mav;
    }

    @GetMapping("addCategory")
    public ModelAndView addCategories() {
        ModelAndView mav = new ModelAndView("addCategoryPh.html");
        Category newCategory = new Category();
        mav.addObject("category", newCategory);
        return mav;
    }
    @PostMapping("addCategory")
    public ModelAndView saveCategory(@ModelAttribute Category category) {
        this.categoryRepository.save(category);
        return new ModelAndView("redirect:/Pharmacist/addCategory");
    }

    @GetMapping("editCategory/{id}")
    public ModelAndView editCategory(@PathVariable("id") int id) {
        ModelAndView mav = new ModelAndView("editCategoryPh.html");
        Category category = this.categoryRepository.findById(id); 
        mav.addObject("category", category);
        return mav;
    }
    @PostMapping("editCategory/{id}")
    public ModelAndView editCategory(@PathVariable("id") int id, @ModelAttribute Category updatedCategory) {
        Category existingCategory = this.categoryRepository.findById(id);
        existingCategory.setName(updatedCategory.getName());
        existingCategory.setImage(updatedCategory.getImage());
        categoryRepository.save(existingCategory); 
        return new ModelAndView("redirect:/Pharmacist/categories"); 
    }

    @GetMapping("deleteCategory/{id}")
    public ModelAndView deleteCategory(@PathVariable("id") int id) {
        this.categoryRepository.deleteById(id);
        return new ModelAndView("redirect:/Pharmacist/categories");
    }

    
    @GetMapping("products")
    public ModelAndView getAllProducts() {
        ModelAndView mav = new ModelAndView("productsPh.html");
        List<Product> products = this.productRepository.findAll();
        mav.addObject("products", products);
        return mav;
    }

    @GetMapping("addProduct")
    public ModelAndView addProduct() {
        ModelAndView mav = new ModelAndView("addProductPh.html");
        List<Category> allCategories = this.categoryRepository.findAll();
        mav.addObject("allCategories", allCategories);
        Product newProduct = new Product();
        mav.addObject("product", newProduct);
        return mav;
    }
    @PostMapping("addProduct")
    public ModelAndView saveProduct(@ModelAttribute Product product) {
        this.productRepository.save(product);
        return new ModelAndView("redirect:/Pharmacist/addProduct");
    }

    @GetMapping("editProduct/{id}")
    public ModelAndView editProduct(@PathVariable("id") int id) {
        ModelAndView mav = new ModelAndView("editProductPh.html");
        Product product = this.productRepository.findById(id); 
        List<Category> allCategories = this.categoryRepository.findAll();
        mav.addObject("allCategories", allCategories);
        mav.addObject("product", product);
        return mav;
    }
    @PostMapping("editProduct/{id}")
    public ModelAndView editProduct(@PathVariable("id") int id, @ModelAttribute Product updatedProduct) {
        Product existingProduct = this.productRepository.findById(id);
        existingProduct.setName(updatedProduct.getName());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setImage(updatedProduct.getImage());
        existingProduct.setActiveIngredient(updatedProduct.getActiveIngredient());
        existingProduct.setSideEffect(updatedProduct.getSideEffect());
        existingProduct.setQuantity(updatedProduct.getQuantity());
        existingProduct.setCategory(updatedProduct.getCategory());

        productRepository.save(existingProduct); 
        return new ModelAndView("redirect:/Pharmacist/products"); 
    }

    @GetMapping("deleteProduct/{id}")
    public ModelAndView deleteProduct(@PathVariable("id") int id) {
        this.productRepository.deleteById(id);
        return new ModelAndView("redirect:/Pharmacist/products");
    }
}

    

