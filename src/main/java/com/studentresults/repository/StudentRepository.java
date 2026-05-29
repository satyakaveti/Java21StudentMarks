package com.studentresults.repository;

import com.studentresults.domain.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository
        extends JpaRepository<Student, Long>, JpaSpecificationExecutor<Student> {

    Optional<Student> findByStudentCode(String studentCode);

    Optional<Student> findByEmail(String email);

    boolean existsByStudentCode(String studentCode);

    boolean existsByEmail(String email);

    Page<Student> findAllByDeletedFalse(Pageable pageable);

    /** Full-text search on student name — native PostgreSQL query. */
    @Query(value = """
            SELECT * FROM students
            WHERE deleted = false
              AND (first_name ILIKE '%' || :query || '%'
               OR last_name  ILIKE '%' || :query || '%')
            """, nativeQuery = true)
    Page<Student> searchByName(@Param("query") String query, Pageable pageable);
}
