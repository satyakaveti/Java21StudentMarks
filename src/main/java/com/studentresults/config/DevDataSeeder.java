package com.studentresults.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * Dev-only data seeder — runs after application startup in "dev" profile.
 * Seeds: 1 ADMIN user, 3 STUDENT users with marks for all 3 subjects.
 *
 * @Profile("dev") — never runs in test or prod.
 */
@Component
@Profile("dev")
public class DevDataSeeder implements ApplicationRunner {

    private static final Logger log = LoggerFactory.getLogger(DevDataSeeder.class);

    // TODO: inject StudentRepository, UserRepository, SubjectRepository,
    //        MarkRepository, PasswordEncoder

    @Override
    public void run(ApplicationArguments args) {
        log.info("DevDataSeeder: seeding development data...");
        // TODO: implement seed logic
        //  1. Skip if data already exists (existsByStudentCode check)
        //  2. Create admin user
        //  3. Create 3 students with marks
        log.info("DevDataSeeder: done");
    }
}
