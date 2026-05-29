package com.studentresults.service.impl;

import com.studentresults.aspect.Auditable;
import com.studentresults.dto.CreateStudentRequest;
import com.studentresults.dto.ServiceResult;
import com.studentresults.dto.StudentDTO;
import com.studentresults.dto.UpdateStudentRequest;
import com.studentresults.service.StudentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    // TODO: inject StudentRepository, UserRepository, ApplicationEventPublisher

    @Override
    @Auditable
    public ServiceResult<StudentDTO> createStudent(CreateStudentRequest request) {
        // TODO: implement
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    @Transactional(readOnly = true)
    public ServiceResult<StudentDTO> getStudentById(Long id) {
        // TODO: implement
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    @Transactional(readOnly = true)
    public ServiceResult<StudentDTO> getStudentByCode(String studentCode) {
        // TODO: implement
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    @Transactional(readOnly = true)
    public ServiceResult<Page<StudentDTO>> getAllStudents(Pageable pageable) {
        // TODO: implement
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    @Transactional(readOnly = true)
    public ServiceResult<Page<StudentDTO>> searchStudents(String query, Pageable pageable) {
        // TODO: implement
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    @Auditable
    public ServiceResult<StudentDTO> updateStudent(Long id, UpdateStudentRequest request) {
        // TODO: implement
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    @Auditable
    public ServiceResult<Void> deleteStudent(Long id) {
        // TODO: implement
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
