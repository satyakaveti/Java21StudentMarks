package com.studentresults.controller;

import com.studentresults.dto.MarkDTO;
import com.studentresults.dto.ResultSummaryDTO;
import com.studentresults.security.UserPrincipal;
import com.studentresults.service.ResultService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;

@RestController
@RequestMapping("/api/results")
public class ResultController {

    private final ResultService resultService;

    public ResultController(ResultService resultService) {
        this.resultService = resultService;
    }

    /** GET /api/results/me — own marks for all 3 subjects. */
    @GetMapping("/me")
    public ResponseEntity<List<MarkDTO>> getMyResults(
            @AuthenticationPrincipal UserPrincipal principal) {
        // TODO: implement
        return ResponseEntity.ok().build();
    }

    /** GET /api/results/me/summary — total, average, rank. */
    @GetMapping("/me/summary")
    public ResponseEntity<ResultSummaryDTO> getMySummary(
            @AuthenticationPrincipal UserPrincipal principal) {
        // TODO: implement
        return ResponseEntity.ok().build();
    }

    /** GET /api/results/stream — SSE stream for mark-published events. */
    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter streamResults(@AuthenticationPrincipal UserPrincipal principal) {
        // TODO: register emitter, return it
        return new SseEmitter();
    }
}
