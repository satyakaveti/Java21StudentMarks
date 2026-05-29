package com.studentresults.service;

import com.studentresults.dto.CreateMarkRequest;
import com.studentresults.dto.MarkDTO;
import com.studentresults.dto.ResultSummaryDTO;
import com.studentresults.dto.ServiceResult;
import com.studentresults.dto.UpdateMarkRequest;

import java.util.List;

public interface ResultService {

    ServiceResult<List<MarkDTO>> getResultsForStudent(Long studentId);

    ServiceResult<ResultSummaryDTO> getSummaryForStudent(Long studentId);

    ServiceResult<MarkDTO> createMark(Long studentId, CreateMarkRequest request);

    ServiceResult<MarkDTO> updateMark(Long markId, UpdateMarkRequest request);

    ServiceResult<Void> deleteMark(Long markId);
}
