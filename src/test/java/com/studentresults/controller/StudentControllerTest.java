package com.studentresults.controller;

import com.studentresults.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Controller slice test — no DB, no real security filter chain.
 * @WebMvcTest loads only AdminController and MVC infrastructure.
 */
@WebMvcTest(AdminController.class)
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Test
    @WithMockUser(roles = "ADMIN")
    void listStudents_returnsOk() throws Exception {
        // TODO: mock studentService.getAllStudents(), assert 200 + JSON body
        mockMvc.perform(get("/api/admin/students"))
               .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "STUDENT")
    void listStudents_forbiddenForStudent() throws Exception {
        mockMvc.perform(get("/api/admin/students"))
               .andExpect(status().isForbidden());
    }

    @Test
    void listStudents_unauthorizedWithoutToken() throws Exception {
        mockMvc.perform(get("/api/admin/students"))
               .andExpect(status().isUnauthorized());
    }

    // TODO: add tests for createStudent, updateStudent, deleteStudent
}
