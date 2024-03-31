package com.manning.sbip.ch03;

import com.manning.sbip.ch03.model.Course;
import com.manning.sbip.ch03.repository.CustomizedCourseRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

/**
 * 커스텀 리포지토리 동작 검증 단위 테스트
 */
@DataJpaTest
class CourseTrackerSpringBootApplicationTests {

    @Autowired
    private CustomizedCourseRepository customizedCourseRepository;

    @Test
    public void givenCreateCourseWhenFindAllCoursesThenExpectOneCourse() {

        Course course = new Course("Rapid Spring Boot Application Development", "Spring", 4,
                "Spring Boot gives all the power of the Spring Framework without all of the complexities");
        customizedCourseRepository.save(course);

        assertThat(Arrays.asList(customizedCourseRepository.findAll()).size()).isEqualTo(1);
    }
}
