package com.example.aswe.demo;

import com.example.aswe.demo.Controllers.PharmacistController;
import com.example.aswe.demo.Models.Category;
import com.example.aswe.demo.Models.Pharmacist;
import com.example.aswe.demo.Models.Product;
import com.example.aswe.demo.Repositories.CategoryRepository;
import com.example.aswe.demo.Repositories.PharmacistRepository;
import com.example.aswe.demo.Repositories.ProductRepository;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class PharmacistControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private PharmacistRepository pharmacistRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private PharmacistController pharmacistController;

    @Mock
    private HttpSession session;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(pharmacistController).build();
    }

    @Test
    public void testPharmacistIndex() throws Exception {
        mockMvc.perform(get("/Pharmacist"))
                .andExpect(status().isOk())
                .andExpect(view().name("pharmacist.html"));
    }

    @Test
    public void testLogin1() throws Exception {
        mockMvc.perform(get("/Pharmacist/login1"))
                .andExpect(status().isOk())
                .andExpect(view().name("login1.html"));
    }

    @Test
    public void testLoginProcess_ValidCredentials() throws Exception {
        Pharmacist pharmacist = new Pharmacist();
        pharmacist.setUsername("validUser");
        pharmacist.setPassword(BCrypt.hashpw("validPassword", BCrypt.gensalt()));

        when(pharmacistRepository.findByUsername("validUser")).thenReturn(pharmacist);

        mockMvc.perform(post("/Pharmacist/login1")
                .param("username", "validUser")
                .param("password", "validPassword"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/Pharmacist"));
    }

    @Test
    public void testLoginProcess_InvalidCredentials() throws Exception {
        Pharmacist pharmacist = new Pharmacist();
        pharmacist.setUsername("validUser");
        pharmacist.setPassword(BCrypt.hashpw("validPassword", BCrypt.gensalt()));

        when(pharmacistRepository.findByUsername("validUser")).thenReturn(pharmacist);

        mockMvc.perform(post("/Pharmacist/login1")
                .param("username", "validUser")
                .param("password", "invalidPassword"))
                .andExpect(status().isOk())
                .andExpect(view().name("login1.html"))
                .andExpect(model().attribute("loginError", "Incorrect password"));
    }

    @Test
    public void testGetAllCategories() throws Exception {
        List<Category> categories = Arrays.asList(new Category(), new Category());
        when(categoryRepository.findAll()).thenReturn(categories);

        mockMvc.perform(get("/Pharmacist/categories"))
                .andExpect(status().isOk())
                .andExpect(view().name("listCategoryPh.html"))
                .andExpect(model().attribute("categories", hasSize(2)));
    }

    @Test
    public void testSaveCategory_Valid() throws Exception {
        Category category = new Category();
        category.setName("TestCategory");
        category.setImage("testImage.jpg");

        mockMvc.perform(post("/Pharmacist/addCategory")
                .flashAttr("category", category))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/Pharmacist/addCategory"));

        verify(categoryRepository, times(1)).save(category);
    }

    @Test
    public void testSaveCategory_Invalid() throws Exception {
        Category category = new Category();
        category.setName("");
        category.setImage("");

        mockMvc.perform(post("/Pharmacist/addCategory")
                .flashAttr("category", category))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/Pharmacist/addCategory"));

        verify(categoryRepository, never()).save(any(Category.class));
    }

    @Test
    public void testGetAllProducts() throws Exception {
        List<Product> products = Arrays.asList(new Product(), new Product());
        when(productRepository.findAll()).thenReturn(products);

        mockMvc.perform(get("/Pharmacist/products"))
                .andExpect(status().isOk())
                .andExpect(view().name("productsPh.html"))
                .andExpect(model().attribute("products", hasSize(2)));
    }

    @Test
    public void testSaveProduct_Valid() throws Exception {
        Product product = new Product();
        product.setName("TestProduct");
        product.setPrice(100.0);
        product.setQuantity(10);

        mockMvc.perform(post("/Pharmacist/addProduct")
                .flashAttr("product", product)
                .param("image", "testImage.jpg"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/Pharmacist/products"));

        verify(productRepository, times(1)).save(product);
    }

    // @Test
    // public void testSaveProduct_Invalid() throws Exception {
    //     Product product = new Product();
    //     product.setName("");
    //     product.setPrice(-1.0);
    //     product.setQuantity(-1);

    //     mockMvc.perform(post("/Pharmacist/addProduct")
    //             .flashAttr("product", product)
    //             .param("image", ""))
    //             .andExpect(status().isOk())  // Expecting 200 OK status
    //             .andExpect(view().name("addProductPh.html"))  // Expecting the form view
    //             .andExpect(model().attributeExists("nameError"))
    //             .andExpect(model().attributeExists("priceError"))
    //             .andExpect(model().attributeExists("quantityError"));

    //     verify(productRepository, never()).save(any(Product.class));
    // }
}

