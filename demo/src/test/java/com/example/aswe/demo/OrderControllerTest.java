package com.example.aswe.demo;

import com.example.aswe.demo.Controllers.OrderController;
import com.example.aswe.demo.Models.Orders;
import com.example.aswe.demo.Repositories.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class OrderControllerTest {

    private MockMvc mockMvc;

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
    }

    @Test
    public void testGetOrders() throws Exception {
        // Given
        Orders order1 = new Orders();
        order1.setId(1); // Assuming setId method is available
        Orders order2 = new Orders();
        order2.setId(2); // Assuming setId method is available

        List<Orders> orders = Arrays.asList(order1, order2);

        // When
        when(orderRepository.findAll()).thenReturn(orders);

        // Then
        mockMvc.perform(get("/Admin/orders"))
                .andExpect(status().isOk())
                .andExpect(view().name("orders.html"))
                .andExpect(model().attribute("orders", hasSize(2)))
                .andExpect(model().attribute("orders", is(orders)));

        // Verify that the repository's findAll method was called
        verify(orderRepository).findAll();
    }
}
