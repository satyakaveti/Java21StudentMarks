package com.studentresults.integration;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;

/**
 * Full-stack integration test.
 * Testcontainers spins up a real PostgreSQL container.
 * @ServiceConnection (Boot 3.1) wires the datasource automatically.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@ActiveProfiles("test")
class StudentResultsIntegrationTest {

    @LocalServerPort
    private int port;

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres =
            new PostgreSQLContainer<>("postgres:16-alpine");

    @Test
    void contextLoads() {
        // Verifies the full application context starts without errors
    }

    // TODO: add end-to-end tests — login → create student → enter marks → fetch results
}
