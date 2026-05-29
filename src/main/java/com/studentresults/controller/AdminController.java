package com.studentresults.controller;

import com.studentresults.dto.*;
import com.studentresults.service.ResultService;
import com.studentresults.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final StudentService studentService;
    private final ResultService resultService;

    public AdminController(StudentService studentService, ResultService resultService) {
        this.studentService = studentService;
        this.resultService = resultService;
    }

    // ── Student management ─────────────────────────────────────────

    @GetMapping("/students")
    public ResponseEntity<Page<StudentDTO>> listStudents(
            @RequestParam(required = false) String search,
            @PageableDefault(size = 20) Pageable pageable) {
        // TODO: implement
        return ResponseEntity.ok().build();
    }

    @PostMapping("/students")
    public ResponseEntity<StudentDTO> createStudent(
            @Valid @RequestBody CreateStudentRequest request) {
        // TODO: implement
        return ResponseEntity.ok().build();
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<StudentDTO> getStudent(@PathVariable Long id) {
        // TODO: implement
        return ResponseEntity.ok().build();
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<StudentDTO> updateStudent(
            @PathVariable Long id,
            @Valid @RequestBody UpdateStudentRequest request) {
        // TODO: implement
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        // TODO: implement
        return ResponseEntity.noContent().build();
    }

    // ── Marks management ───────────────────────────────────────────

    @PostMapping("/students/{id}/marks")
    public ResponseEntity<MarkDTO> createMark(
            @PathVariable Long id,
            @Valid @RequestBody CreateMarkRequest request) {
        // TODO: implement
        return ResponseEntity.ok().build();
    }

    @PutMapping("/marks/{markId}")
    public ResponseEntity<MarkDTO> updateMark(
            @PathVariable Long markId,
            @Valid @RequestBody UpdateMarkRequest request) {
        // TODO: implement
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/marks/{markId}")
    public ResponseEntity<Void> deleteMark(@PathVariable Long markId) {
        // TODO: implement
        return ResponseEntity.noContent().build();
    }
}
