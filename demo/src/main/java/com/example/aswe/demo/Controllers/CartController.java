package com.example.aswe.demo.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.aswe.demo.Models.Cart;
import com.example.aswe.demo.Repositories.CartRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CartController {
    @Autowired
    private CartRepository cartRepository;

    @GetMapping("/cart")
    public ModelAndView getCart() {
        ModelAndView mav = new ModelAndView("cart.html");
         List<Cart> cart = this.cartRepository.findAll();
         mav.addObject("cart", cart);
         return mav;
    }
    
}
