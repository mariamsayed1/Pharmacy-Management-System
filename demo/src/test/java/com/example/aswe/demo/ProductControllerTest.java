package com.example.aswe.demo;

import com.example.aswe.demo.Controllers.ProductController;
import com.example.aswe.demo.Models.Product;
import com.example.aswe.demo.Repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ProductControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    public void testSearchProducts() throws Exception {
        // Given
        Product product1 = new Product();
        product1.setName("Apple");
        Product product2 = new Product();
        product2.setName("Apricot");

        List<Product> products = Arrays.asList(product1, product2);
        String searchTerm = "Ap";

        // When
        when(productRepository.findByNameStartingWithIgnoreCase(searchTerm)).thenReturn(products);

        // Then
        mockMvc.perform(get("/search")
                .param("term", searchTerm)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("Apple")))
                .andExpect(jsonPath("$[1].name", is("Apricot")));
    }
}
