package com.manning.sbip.ch03;

import com.manning.sbip.ch03.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

@SpringBootApplication
public class CourseTrackerSpringBootApplication implements CommandLineRunner {

    private static Logger logger =
            LoggerFactory.getLogger(CourseTrackerSpringBootApplication.class);

   public static void main(String[] args) {
        SpringApplication.run(CourseTrackerSpringBootApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        User user1 = new User("rokwon11", "rokwon");

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user1);

        logger.error("Password for user1 do not adhere to the password policy");
        violations.forEach(userConstraintViolation -> logger.error("Violation details: [{}].", userConstraintViolation.getMessage()));

        User user2 = new User("rokwon22", "rokwon22%UAsd");
        violations = validator.validate(user2);
        if (violations.isEmpty()){
            logger.info("Password for user2 adhere to the password policy");
        }

        User user3 = new User("rokwon33", "rokwon22$4UDasss");
        violations = validator.validate(user3);
        logger.error("Password for user3 violates maximum repetitive rule");
        violations.forEach(userConstraintViolation -> logger.error("Violation details: [{}].", userConstraintViolation.getMessage()));

        User user4 = new User("rokwon44", "rokwon224FDgggg");
        violations = validator.validate(user4);
        logger.error("Password for user4 violates maximum repetitive rule");
        violations.forEach(userConstraintViolation -> logger.error("Violation details: [{}].", userConstraintViolation.getMessage()));
    }
}