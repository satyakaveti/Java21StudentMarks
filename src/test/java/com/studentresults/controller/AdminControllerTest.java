package com.studentresults.controller;

import com.studentresults.service.ResultService;
import com.studentresults.service.StudentService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Controller slice test for AdminController — mark grade boundary tests.
 */
@WebMvcTest(AdminController.class)
class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @MockBean
    private ResultService resultService;

    /**
     * Grade boundary test — verifies the grading rules:
     * 0–34 → F, 35–49 → C, 50–74 → B, 75–100 → A
     */
    @ParameterizedTest
    @CsvSource({
        "0,   F, false",
        "34,  F, false",
        "35,  C, true",
        "49,  C, true",
        "50,  B, true",
        "74,  B, true",
        "75,  A, true",
        "100, A, true"
    })
    void gradeCalculation_boundaryValues(int score, String expectedGrade, boolean expectedPass) {
        // TODO: call grade calculation utility and assert expectedGrade + expectedPass
    }
}
