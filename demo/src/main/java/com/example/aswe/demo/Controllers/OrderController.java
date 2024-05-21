package com.example.aswe.demo.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.aswe.demo.Models.Orders;
import com.example.aswe.demo.Repositories.OrderRepository;

import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/Admin/orders")
    public ModelAndView getOrders() {
        ModelAndView mav = new ModelAndView("orders.html");
        List<Orders> orders = this.orderRepository.findAll();
        mav.addObject("orders", orders);
        return mav;
    }
}
