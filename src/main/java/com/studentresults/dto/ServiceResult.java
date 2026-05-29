package com.studentresults.dto;

/**
 * Sealed result type — replaces throwing exceptions from service layer.
 * Controllers pattern-match on this with Java 21 switch expressions.
 *
 * Hierarchy:
 *   ServiceResult
 *   ├── Success<T>       — operation succeeded, carries payload
 *   ├── NotFound         — entity not found
 *   ├── Forbidden        — access denied
 *   └── ValidationError  — business rule violation
 */
public sealed interface ServiceResult<T>
        permits ServiceResult.Success, ServiceResult.NotFound,
                ServiceResult.Forbidden, ServiceResult.ValidationError {

    /** Operation succeeded — carries the result payload. */
    record Success<T>(T data) implements ServiceResult<T> {}

    /** Entity was not found. */
    record NotFound<T>(String message) implements ServiceResult<T> {}

    /** Caller is not allowed to perform this action. */
    record Forbidden<T>(String message) implements ServiceResult<T> {}

    /** A business rule was violated (e.g. duplicate roll number). */
    record ValidationError<T>(String field, String message) implements ServiceResult<T> {}
}
