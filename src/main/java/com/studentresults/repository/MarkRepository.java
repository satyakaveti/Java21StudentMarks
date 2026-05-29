package com.studentresults.repository;

import com.studentresults.domain.Mark;
import com.studentresults.repository.projection.ResultSummaryProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
public interface MarkRepository extends JpaRepository<Mark, Long> {

    List<Mark> findByStudentId(Long studentId);

    Optional<Mark> findByStudentIdAndSubjectId(Long studentId, Long subjectId);

    boolean existsByStudentIdAndSubjectId(Long studentId, Long subjectId);

    /** Average score per subject for a student — JPQL. */
    @Query("""
            SELECT AVG(m.score)
            FROM Mark m
            WHERE m.student.id = :studentId AND m.subject.id = :subjectId
            """)
    Double averageScoreByStudentAndSubject(@Param("studentId") Long studentId,
                                           @Param("subjectId") Long subjectId);

    /** Interface projection — id, subject, mark, grade only. */
    @Query("""
            SELECT m.id AS id, m.subject.name AS subjectName,
                   m.score AS score, m.grade AS grade
            FROM Mark m
            WHERE m.student.id = :studentId
            """)
    List<ResultSummaryProjection> findResultSummaryByStudentId(@Param("studentId") Long studentId);

    /** Stream all marks for bulk report generation. */
    @Query("SELECT m FROM Mark m JOIN FETCH m.student JOIN FETCH m.subject")
    Stream<Mark> streamAllWithDetails();
}
