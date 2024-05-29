package com.example.aswe.demo.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.aswe.demo.Models.Cart;
import com.example.aswe.demo.Models.CartItem;
import com.example.aswe.demo.Models.Product;
import com.example.aswe.demo.Repositories.CartItemRepository;
import com.example.aswe.demo.Repositories.ProductRepository;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/cart")
public class CartController {
    // @Autowired
    // private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    // @Autowired
    // private UserRepository userRepository;


    // @GetMapping("/cart")
    // public ModelAndView getCart() {
    //     ModelAndView mav = new ModelAndView("cart.html");
    //      List<Cart> cart = this.cartRepository.findAll();
    //      mav.addObject("cart", cart);
    //      return mav;
    // }

    @Autowired
    private ProductRepository productRepository;

    // @ModelAttribute("")
    // public Cart cart() {
    //     return new Cart();
    // }

    @GetMapping("")
    public ModelAndView viewCart(@ModelAttribute("cart") Cart cart, HttpSession session) {
        ModelAndView mav = new ModelAndView("cart.html");
        
        //int currentUser = (Integer) session.getAttribute("id");
        //userRepository.findById(currentUser);
        //cart.setUser(userRepository.findById(currentUser));
        List<CartItem> cartItems = this.cartItemRepository.findAll();
        mav.addObject("cartItems", cartItems);
        return mav;
    }
    // @ModelAttribute("")
    // public Cart cart(HttpSession session) {
    //     // Retrieve cart from session or create a new one if it doesn't exist
    //     Cart cart = (Cart) session.getAttribute("cart");
    //     if (cart == null) {
    //         cart = new Cart();
    //         session.setAttribute("cart", cart);
    //     }
    //     return cart;
    // }
    

    @GetMapping("/add-to-cart/{id}")
    public ModelAndView addToCart(@PathVariable int id, @ModelAttribute("cart") Cart cart) {
        ModelAndView mav = new ModelAndView("cart.html");
        Product product = productRepository.findById(id);
        CartItem cartItem = new CartItem(product, 1);
        double subTotal = cartItem.getTotalPrice();
        cartItem.setSubTotal(subTotal);
        cartItemRepository.save(cartItem);
        cart.addItem(product);
        //List<>cart.getItems();
        //cartRepository.save();
        double total = cart.getTotal();
        List<CartItem> cartItems = this.cartItemRepository.findAll();
        mav.addObject("cartItems", cartItems);
        mav.addObject("SubTotal", subTotal);
        mav.addObject("total", total);
        return mav;
        // return new ModelAndView("redirect:/cart");

        // return "redirect:/cart";
    }

    @GetMapping("/remove-from-cart/{id}")
    public ModelAndView removeFromCart(@PathVariable int id, @ModelAttribute("cart") Cart cart) {
        //Product product = productRepository.findById(id);
        cartItemRepository.deleteById(id);
        //cart.removeItem(product);
        return new ModelAndView("redirect:/cart");

        // return "redirect:/cart";
    }
}