package com.studentresults.service.impl;

import com.studentresults.aspect.Auditable;
import com.studentresults.dto.CreateMarkRequest;
import com.studentresults.dto.MarkDTO;
import com.studentresults.dto.ResultSummaryDTO;
import com.studentresults.dto.ServiceResult;
import com.studentresults.dto.UpdateMarkRequest;
import com.studentresults.service.ResultService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ResultServiceImpl implements ResultService {

    // TODO: inject MarkRepository, StudentRepository, SubjectRepository,
    //        ApplicationEventPublisher, MeterRegistry

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "results", key = "#studentId")
    public ServiceResult<List<MarkDTO>> getResultsForStudent(Long studentId) {
        // TODO: implement
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    @Transactional(readOnly = true)
    public ServiceResult<ResultSummaryDTO> getSummaryForStudent(Long studentId) {
        // TODO: implement
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    @Auditable
    @CachePut(value = "results", key = "#studentId")
    public ServiceResult<MarkDTO> createMark(Long studentId, CreateMarkRequest request) {
        // TODO: implement
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    @Auditable
    public ServiceResult<MarkDTO> updateMark(Long markId, UpdateMarkRequest request) {
        // TODO: implement
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    @Auditable
    @CacheEvict(value = "results", allEntries = false)
    public ServiceResult<Void> deleteMark(Long markId) {
        // TODO: implement
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
