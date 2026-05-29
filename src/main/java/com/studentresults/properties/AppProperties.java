package com.studentresults.properties;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

/**
 * Strongly-typed config properties — bound from application.yml prefix "app".
 * Nested records group related settings (Jwt, Email, Cors).
 *
 * Java 16 record + @ConfigurationProperties — Boot 3.x supports records natively.
 */
@ConfigurationProperties(prefix = "app")
@Validated
public record AppProperties(

        @Valid Jwt jwt,
        @Valid Email email,
        @Valid Cors cors

) {

    public record Jwt(
            @NotBlank String secret,
            @Positive long accessTokenExpiryMs,
            @Positive long refreshTokenExpiryMs
    ) {}

    public record Email(
            @NotBlank String from,
            boolean enabled
    ) {}

    public record Cors(
            @NotBlank String allowedOrigins
    ) {}
}
