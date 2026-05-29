package com.studentresults.repository.projection;

/**
 * Interface projection — Spring Data JPA returns a proxy with only these fields.
 * Avoids loading the full Mark + Student + Subject graph for summary views.
 */
public interface ResultSummaryProjection {
    Long getId();
    String getSubjectName();
    Integer getScore();
    String getGrade();
}
