package com.example.aswe.demo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.servlet.ModelAndView;

import com.example.aswe.demo.Controllers.AdminController;
import com.example.aswe.demo.Models.Category;
import com.example.aswe.demo.Models.Pharmacist;
import com.example.aswe.demo.Models.Product;
import com.example.aswe.demo.Models.UserLog;
import com.example.aswe.demo.Repositories.CategoryRepository;
import com.example.aswe.demo.Repositories.PharmacistRepository;
import com.example.aswe.demo.Repositories.ProductRepository;
import com.example.aswe.demo.Repositories.UserLogRepository;

public class AdminControllerTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private PharmacistRepository pharmacistRepository;

    @Mock
    private UserLogRepository userLogRepository;

    @InjectMocks
    private AdminController adminController;




    @SuppressWarnings("deprecation")
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAdminIndex() {
        // Mock
        ModelAndView expectedModelAndView = new ModelAndView("admin.html");

        // Test
        ModelAndView actualModelAndView = adminController.adminIndex();

        // Verify
        assertEquals(expectedModelAndView.getViewName(), actualModelAndView.getViewName());
    }

    @Test
    public void testGetAllCategories() {
        // Mock
        List<Category> categories = new ArrayList<>();
        categories.add(new Category());
        categories.add(new Category());
        when(categoryRepository.findAll()).thenReturn(categories);

        // Test
        ModelAndView mav = adminController.getAllCategories();

        // Verify
        assertEquals("listCategory.html", mav.getViewName());
        assertEquals(categories, mav.getModel().get("categories"));
    }

    @Test
    public void testAddCategories() {
        // Test
        ModelAndView mav = adminController.addCategories();

        // Verify
        assertEquals("addCategory.html", mav.getViewName());
        assertNotNull(mav.getModel().get("category"));
    }

    @Test
public void testSaveCategory() {
    // Create a non-empty Category object
    Category category = new Category();
    category.setName("Test Category");
    category.setImage("test-image.jpg");

    // Test
    ModelAndView mav = adminController.saveCategory(category);

    // Verify
    verify(categoryRepository, times(1)).save(category);
    assertEquals("redirect:/Admin/addCategory", mav.getViewName());
}
    @Test
    public void testEditCategory() {
        // Mock
        Category category = new Category();
        when(categoryRepository.findById(1)).thenReturn(category);

        // Test
        ModelAndView mav = adminController.editCategory(1);

        // Verify
        assertEquals("editCategory.html", mav.getViewName());
        assertEquals(category, mav.getModel().get("category"));
    }

    @Test
    public void testDeleteCategory() {
        // Test
        ModelAndView mav = adminController.deleteCategory(1);

        // Verify
        verify(categoryRepository, times(1)).deleteById(1);
        assertEquals("redirect:/Admin/categories", mav.getViewName());
    }

    @Test
public void testGetAllProducts() {
    // Mock
    List<Product> expectedProducts = new ArrayList<>();
    expectedProducts.add(new Product());
    expectedProducts.add(new Product());
    Page<Product> pageProducts = new PageImpl<>(expectedProducts);
    when(productRepository.findAll(PageRequest.of(0, 3))).thenReturn(pageProducts);

    // Test
    ModelAndView mav = adminController.getAllProducts(1);

    // Extract actual products from the page
    List<Product> actualProducts = pageProducts.getContent();

    // Verify
    assertEquals("products.html", mav.getViewName());
    assertEquals(expectedProducts, actualProducts);
    assertEquals(1, mav.getModel().get("currentPage"));
    assertEquals(1, mav.getModel().get("totalPages"));
}
    @Test
    public void testAddProduct() {
        // Mock
        List<Category> allCategories = new ArrayList<>();
        allCategories.add(new Category());
        allCategories.add(new Category());
        when(categoryRepository.findAll()).thenReturn(allCategories);
        Product newProduct = new Product();

        // Test
        ModelAndView mav = adminController.addProduct();

        // Verify
        assertEquals("addProduct.html", mav.getViewName());
        assertEquals(allCategories, mav.getModel().get("allCategories"));
        assertEquals(newProduct, mav.getModel().get("product"));
    }

    @Test
    public void testSaveProduct() {
        // Mock
        Product product = new Product();

        // Test
        ModelAndView mav = adminController.saveProduct(product, "image.jpg");

        // Verify
        verify(productRepository, times(1)).save(product);
        assertEquals("redirect:/Admin/addProduct", mav.getViewName());
    }

    @Test
    public void testEditProduct() {
        // Mock
        Product existingProduct = new Product();
        when(productRepository.findById(1)).thenReturn(existingProduct);

        // Test
        ModelAndView mav = adminController.editProduct(1);

        // Verify
        assertEquals("editProduct.html", mav.getViewName());
        assertEquals(existingProduct, mav.getModel().get("product"));
    }

    @Test
    public void testDeleteProduct() {
        // Test
        ModelAndView mav = adminController.deleteProduct(1);

        // Verify
        verify(productRepository, times(1)).deleteById(1);
        assertEquals("redirect:/Admin/products", mav.getViewName());
    }

    @Test
    public void testAddPharmacist() {
        // Test
        ModelAndView mav = adminController.addPharmacist();

        // Verify
        assertEquals("addPharmacist.html", mav.getViewName());
        assertNotNull(mav.getModel().get("pharmacist"));
    }

    @Test
    public void testSavePharmacist_PasswordTooShort() {
        // Mock
        Pharmacist pharmacist = new Pharmacist();
        pharmacist.setPassword("short"); // Password length less than 8

        // Test
        ModelAndView mav = adminController.savePharmacist(pharmacist);

        // Verify
        assertNotNull(mav);
        assertTrue((Boolean) mav.getModel().get("hasPasswordLengthError"));
        assertEquals("Password is too short (minimum 8 characters)", mav.getModel().get("passwordLengthError"));
        assertEquals("listPharmacist.html", mav.getViewName());
    }

   
    @Test
    public void testGetAllPharmacists() {
        // Mock
        List<Pharmacist> pharmacists = new ArrayList<>();
        pharmacists.add(new Pharmacist());
        pharmacists.add(new Pharmacist());
        when(pharmacistRepository.findAll()).thenReturn(pharmacists);

        // Test
        ModelAndView mav = adminController.getAllPharmacists();

        // Verify
        assertEquals("listPharmacist.html", mav.getViewName());
        assertEquals(pharmacists, mav.getModel().get("pharmacists"));
    }

    @Test
    public void testDeletePharmacist() {
        // Mock
        int pharmacistId = 1;

        // Test
        ModelAndView mav = adminController.deletePharmacist(pharmacistId);

        // Verify
        assertNotNull(mav);
        verify(pharmacistRepository, times(1)).deleteById(pharmacistId);
        assertEquals("redirect:/Admin/pharmacists", mav.getViewName());
    }
        
        @Test
        public void testGetAllUserLogs() {
            // Mock
            List<UserLog> userLogs = new ArrayList<>();
            userLogs.add(new UserLog());
            userLogs.add(new UserLog());
            when(userLogRepository.findAll()).thenReturn(userLogs);
        
            // Test
            ModelAndView mav = adminController.getAllUserLogs();
        
            // Verify
            assertEquals("logs.html", mav.getViewName());
            assertEquals(userLogs, mav.getModel().get("Logs"));
        }
    }
