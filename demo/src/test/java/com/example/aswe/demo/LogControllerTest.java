package com.example.aswe.demo;

import com.example.aswe.demo.Controllers.LogController;
import com.example.aswe.demo.Models.UserLog;
import com.example.aswe.demo.Repositories.UserLogRepository;
import jakarta.servlet.http.HttpSession;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class LogControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserLogRepository userLogRepository;

    @InjectMocks
    private LogController logController;

    @Mock
    private HttpSession session;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(logController).build();
    }

    @Test
    public void testViewLogs_WhenUserIsAdmin() throws Exception {
        // Given
        List<UserLog> userLogs = Arrays.asList(new UserLog(), new UserLog());
        when(session.getAttribute("usertype")).thenReturn("admin");
        when(userLogRepository.findAll()).thenReturn(userLogs);

        // When & Then
        mockMvc.perform(get("/admin/logs").sessionAttr("usertype", "admin"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin_logs"))
                .andExpect(model().attribute("userLogs", hasSize(2)))
                .andExpect(model().attribute("userLogs", is(userLogs)));

        // Verify that the repository's findAll method was called
        verify(userLogRepository).findAll();
    }

    @Test
    public void testViewLogs_WhenUserIsNotAdmin() throws Exception {
        // Given
        when(session.getAttribute("usertype")).thenReturn("user");

        // When & Then
        mockMvc.perform(get("/admin/logs").sessionAttr("usertype", "user"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));

        // Verify that the repository's findAll method was not called
        verify(userLogRepository, org.mockito.Mockito.never()).findAll();
    }

    @Test
    public void testViewLogs_WhenUserTypeIsNull() throws Exception {
        // Given
        when(session.getAttribute("usertype")).thenReturn(null);

        // When & Then
        mockMvc.perform(get("/admin/logs"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));

        // Verify that the repository's findAll method was not called
        verify(userLogRepository, org.mockito.Mockito.never()).findAll();
    }
}
