package com.example.but_trucker2;

import com.example.but_trucker2.entity.ErrorEntity;
import com.example.but_trucker2.entity.ErrorStateChangingHistoryEntity;
import com.example.but_trucker2.entity.ProjectEntity;
import com.example.but_trucker2.entity.UserEntity;
import com.example.but_trucker2.entity.termsDirectories.ErrorCriticality;
import com.example.but_trucker2.entity.termsDirectories.ErrorState;
import com.example.but_trucker2.repository.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
//import org.apache.commons.lang.RandomStringUtils;
@Configuration
class DBInitializer {

    private static final Logger log = LoggerFactory.getLogger(DBInitializer.class);


    @Autowired
    PasswordEncoder encoder;

    @Bean
    CommandLineRunner initDatabase(
            ProjectRepo projectRepo,
            CommentRepo commentRepo,
            ErrorRepo errorRepo,
            ErrorStateHistoryRepo errorStateHistoryRepo,
            ResponsiblesRepo responsibleRepo,
            UserRepo userRepo
    ) {
        int iterationsCount = 10;
        for(int i = 0; i < iterationsCount; i++){
            UserEntity user = new UserEntity(
                    RandomStringUtils.random(10, true, false),
                    RandomStringUtils.random(10, true, false),
                    RandomStringUtils.random(10, true, false),
                    RandomStringUtils.random(10, true, true)+"@gmail.com",
                    null,
                    RandomStringUtils.random(10, true, false),
                    encoder.encode("123"),
                    List.of("USER"));
            userRepo.save(user);

            UserEntity developer = new UserEntity(
                    RandomStringUtils.random(10, true, false),
                    RandomStringUtils.random(10, true, false),
                    RandomStringUtils.random(10, true, false),
                    RandomStringUtils.random(10, true, true)+"@gmail.com",
                    RandomStringUtils.random(10, true, false),
                    RandomStringUtils.random(10, true, false),
                    encoder.encode("123"),
                    List.of("DEV","USER"));
            userRepo.save(developer);

            UserEntity admin = new UserEntity(
                    RandomStringUtils.random(10, true, false),
                    RandomStringUtils.random(10, true, false),
                    RandomStringUtils.random(10, true, false),
                    RandomStringUtils.random(10, true, true)+"@gmail.com",
                    null,
                    RandomStringUtils.random(10, true, false),
                    encoder.encode("123"),
                    List.of("DEV","USER", "ADMIN"));
            userRepo.save(admin);
        }

        UserEntity user = new UserEntity(
                "User",
                "Simple",
                "Just",
                "just_simple_user@gmail.com",
                null,
                "user",
                encoder.encode("123"),
                List.of("USER"));
        userRepo.save(user);
        UserEntity developer = new UserEntity(
                "Developer",
                "Regular",
                "That",
                "that_regular_developer@gmail.com",
                "java",
                "developer",
                encoder.encode("123"),
                List.of("DEV","USER"));
        userRepo.save(developer);
        UserEntity admin = new UserEntity(
                "Admin",
                "Only",
                "One",
                "one_and_only_admin@gmail.com",
                null,
                "admin",
                encoder.encode("123"),
                List.of("DEV","USER", "ADMIN"));
        userRepo.save(admin);

        ProjectEntity project1 = new ProjectEntity("project #1", developer);
        projectRepo.save(project1);

        ErrorEntity error = new ErrorEntity(
                "ощибка 1", "описание ошибки", "место возникновения ошибки", ErrorCriticality.CRITICAL, project1, user);
        errorRepo.save(error);

        ErrorStateChangingHistoryEntity historyItem = new ErrorStateChangingHistoryEntity(
                developer, ErrorState.DETECTED, error,
                new Date(2020, Calendar.DECEMBER, 10));
        errorStateHistoryRepo.save(historyItem);

        return args -> {
            log.info("DB init");
            log.info("Preloading " + userRepo.save(user));
            log.info("Preloading " + userRepo.save(developer));
            log.info("Preloading " + userRepo.save(admin));
            log.info("Preloading " + projectRepo.save(project1));
            log.info("Preloading " + errorRepo.save(error));
            log.info("Preloading " + errorStateHistoryRepo.save(historyItem));
        };
    }
}