package com.studentresults.service;

import com.studentresults.dto.CreateStudentRequest;
import com.studentresults.dto.ServiceResult;
import com.studentresults.dto.StudentDTO;
import com.studentresults.dto.UpdateStudentRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StudentService {

    ServiceResult<StudentDTO> createStudent(CreateStudentRequest request);

    ServiceResult<StudentDTO> getStudentById(Long id);

    ServiceResult<StudentDTO> getStudentByCode(String studentCode);

    ServiceResult<Page<StudentDTO>> getAllStudents(Pageable pageable);

    ServiceResult<Page<StudentDTO>> searchStudents(String query, Pageable pageable);

    ServiceResult<StudentDTO> updateStudent(Long id, UpdateStudentRequest request);

    ServiceResult<Void> deleteStudent(Long id);
}
