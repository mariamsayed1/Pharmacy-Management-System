package com.example.aswe.demo;

import com.example.aswe.demo.Controllers.IndexController;
import com.example.aswe.demo.Models.Category;
import com.example.aswe.demo.Models.Product;
import com.example.aswe.demo.Models.User;
import com.example.aswe.demo.Repositories.CategoryRepository;
import com.example.aswe.demo.Repositories.ProductRepository;
import com.example.aswe.demo.Repositories.UserLogRepository;
import com.example.aswe.demo.Repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringJUnitConfig
public class IndexControllerTest {

    @InjectMocks
    private IndexController indexController;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private UserLogRepository userLogRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddUser() {
        ModelAndView mav = indexController.addUser();
        assertEquals("signup.html", mav.getViewName());
        assertNotNull(mav.getModel().get("user"));
    }

    @Test
    public void testSaveUserEmptyFields() {
        User user = new User();
        MockHttpSession session = new MockHttpSession();
        MockHttpServletRequest request = new MockHttpServletRequest();

        ModelAndView mav = indexController.saveUser(user, "", session, request);
        assertEquals("signup.html", mav.getViewName());
        assertNotNull(mav.getModel().get("emptyFieldsError"));
    }

    @Test
    public void testSaveUserValid() {
        User user = new User();
        user.setFullname("John Doe");
        user.setUsername("johndoe");
        user.setEmail("johndoe@example.com");
        user.setPhonenumber("12345678901");
        user.setPassword("password");
        user.setConfirmpassword("password");
        MockHttpSession session = new MockHttpSession();
        MockHttpServletRequest request = new MockHttpServletRequest();

        when(userRepository.save(any(User.class))).thenReturn(user);
        when(userRepository.findByUsername("johndoe")).thenReturn(null);

        ModelAndView mav = indexController.saveUser(user, "user", session, request);
        assertEquals("redirect:/", mav.getViewName());
    }

    @Test
    public void testLogin() {
        ModelAndView mav = indexController.login();
        assertEquals("login.html", mav.getViewName());
    }

    @Test
    public void testLoginProcessInvalidUser() {
        MockHttpSession session = new MockHttpSession();
        MockHttpServletRequest request = new MockHttpServletRequest();

        when(userRepository.findByUsername("invalidUser")).thenReturn(null);

        ModelAndView mav = indexController.loginProcess("invalidUser", "password", session, request);
        assertEquals("login.html", mav.getViewName());
        assertEquals("Username not found", mav.getModel().get("loginError"));
    }

    @Test
    public void testLoginProcessValidUser() {
        MockHttpSession session = new MockHttpSession();
        MockHttpServletRequest request = new MockHttpServletRequest();

        User user = new User();
        user.setUsername("johndoe");
        user.setPassword(BCrypt.hashpw("password", BCrypt.gensalt()));

        when(userRepository.findByUsername("johndoe")).thenReturn(user);

        ModelAndView mav = indexController.loginProcess("johndoe", "password", session, request);
        assertEquals("redirect:/", mav.getViewName());
    }

    @Test
    public void testViewIndex() {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("username", "testuser");

        when(categoryRepository.findAll()).thenReturn(Collections.emptyList());

        ModelAndView mav = indexController.viewIndex(session);
        assertEquals("index.html", mav.getViewName());
        assertEquals("testuser", mav.getModel().get("username"));
        @SuppressWarnings("unchecked")
        List<Category> categories = (List<Category>) mav.getModel().get("categories");
        assertNotNull(categories);
        assertEquals(0, categories.size());
    }

    @Test
    public void testShowUserProfile() {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("username", "testuser");

        ModelAndView mav = indexController.showUserProfile(session);
        assertEquals("profile.html", mav.getViewName());
        assertEquals("testuser", mav.getModel().get("username"));
    }

    @Test
    public void testUpdateProfile() {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("username", "testuser");

        User user = new User();
        user.setUsername("testuser");

        when(userRepository.findByUsername("testuser")).thenReturn(user);
        when(userRepository.findByUsername("newUsername")).thenReturn(null);

        ModelAndView mav = indexController.updateProfile("newUsername", "newFullname", "newEmail", session);
        assertEquals("redirect:/", mav.getViewName());
    }

    @Test
    public void testUpdatePassword() {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("username", "testuser");

        User user = new User();
        user.setUsername("testuser");
        user.setPassword(BCrypt.hashpw("oldPassword", BCrypt.gensalt()));

        when(userRepository.findByUsername("testuser")).thenReturn(user);

        ModelAndView mav = indexController.updatePassword("oldPassword", "newPassword", session);
        assertEquals("redirect:/", mav.getViewName());
    }

    @Test
    public void testDeleteAccount() {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("username", "testuser");

        User user = new User();
        user.setUsername("testuser");

        when(userRepository.findByUsername("testuser")).thenReturn(user);

        ModelAndView mav = indexController.deleteAccount(session);
        assertEquals("redirect:/", mav.getViewName());
    }

    @Test
    public void testGetAllCategories() {
        List<Category> categories = Collections.emptyList();
        when(categoryRepository.findAll()).thenReturn(categories);

        ModelAndView mav = indexController.getAllCategories();
        assertEquals("index.html", mav.getViewName());
        assertEquals(categories, mav.getModel().get("categories"));
    }

    @Test
    public void testGetCategory() {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("username", "testuser");

        Category category = new Category();
        category.setId(1);
        Page<Product> productsPage = new PageImpl<>(Collections.emptyList(), PageRequest.of(0, 3), 0);

        when(categoryRepository.findById(1)).thenReturn(category);
        when(productRepository.findAllByCategoryId(1, PageRequest.of(0, 3))).thenReturn(productsPage);

        ModelAndView mav = indexController.getCategory(1, session, 1);
        assertEquals("category.html", mav.getViewName());
        assertEquals(category, mav.getModel().get("category"));
        assertEquals(productsPage.getContent(), mav.getModel().get("products"));
        assertEquals(1, mav.getModel().get("currentPage"));
        assertEquals(productsPage.getTotalPages(), mav.getModel().get("totalPages"));
    }

    @Test
    public void testGetProductInfo() {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("username", "testuser");

        Product product = new Product();
        product.setId(1);

        when(productRepository.findById(1)).thenReturn(product);

        ModelAndView mav = indexController.getProductInfo(1, session);
        assertEquals("productDetails.html", mav.getViewName());
        assertEquals(product, mav.getModel().get("product"));
    }

    
}

