package com.manning.sbip.ch02;

import com.manning.sbip.ch02.model.Course;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.Validation;
import java.util.Set;


@RestController
@SpringBootApplication
public class CourseTrackerApplication implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(CourseTrackerApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(CourseTrackerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Course course = new Course();
        course.setId(1);
        course.setRating(3);
        //course.setRating(0);
        //course.setRating(6);

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        Set<ConstraintViolation<Course>> violations = validator.validate(course);

        violations.forEach(courseConstraintViolation ->
                logger.error("A constraint has occurred. Violation details: [{}].", courseConstraintViolation)
        );
    }
}