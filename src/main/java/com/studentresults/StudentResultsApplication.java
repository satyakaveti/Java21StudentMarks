package com.studentresults;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableCaching
@EnableAsync
@EnableScheduling
@ConfigurationPropertiesScan
public class StudentResultsApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentResultsApplication.class, args);
    }

    @Bean
    ApplicationRunner printSwaggerLink(Environment env) {
        return args -> {
            String port = env.getProperty("server.port", "8080");
            System.out.println("""
                    ╔══════════════════════════════════════════════════════════════╗
                    ║   Student Portal — started successfully                      ║
                    ║                                                              ║
                    ║   Swagger UI  →  http://localhost:%s/swagger-ui/index.html   ║
                    ║   API Docs    →  http://localhost:%s/v3/api-docs             ║
                    ║   Tutorials   →  http://localhost:%s/docs/tutorials/index.html ║
                    ╚══════════════════════════════════════════════════════════════╝
                    """.formatted(port, port, port));
        };
    }
}
