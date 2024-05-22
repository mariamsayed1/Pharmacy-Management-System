package com.example.aswe.demo.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.aswe.demo.Models.Cart;
import com.example.aswe.demo.Models.Product;
import com.example.aswe.demo.Repositories.ProductRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CartController {
    // @Autowired
    // private CartRepository cartRepository;

    // @Autowired
    // private CartItemRepository cartItemRepository;

    // @GetMapping("/cart")
    // public ModelAndView getCart() {
    //     ModelAndView mav = new ModelAndView("cart.html");
    //      List<Cart> cart = this.cartRepository.findAll();
    //      mav.addObject("cart", cart);
    //      return mav;
    // }

    @Autowired
    private ProductRepository productRepository;

    @ModelAttribute("cart")
    public Cart cart() {
        return new Cart();
    }

    @GetMapping("/cart")
    public ModelAndView viewCart(@ModelAttribute("cart") Cart cartProd) {
        ModelAndView mav = new ModelAndView("cart.html");
        mav.addObject("cartProd", cartProd);
        return mav;
    }

    @GetMapping("/add-to-cart/{id}")
    public ModelAndView addToCart(@PathVariable int id, @ModelAttribute("cart") Cart cart) {
        ModelAndView mav = new ModelAndView("cart.html");
        Product product = productRepository.findById(id);
        cart.addItem(product);
        double total = cart.getTotal();
        mav.addObject("total", total);
        return mav;
        // return new ModelAndView("redirect:/cart");

        // return "redirect:/cart";
    }

    @GetMapping("/remove-from-cart/{id}")
    public ModelAndView removeFromCart(@PathVariable int id, @ModelAttribute("cart") Cart cart) {
        Product product = productRepository.findById(id);
        cart.removeItem(product);
        return new ModelAndView("redirect:/cart");

        // return "redirect:/cart";
    }
}