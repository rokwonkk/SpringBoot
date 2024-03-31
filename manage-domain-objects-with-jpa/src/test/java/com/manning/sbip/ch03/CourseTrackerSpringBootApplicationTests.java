package com.manning.sbip.ch03;

import com.manning.sbip.ch03.model.Course;
import com.manning.sbip.ch03.repository.CourseRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class CourseTrackerSpringBootApplicationTests {

    @Autowired
    private CourseRepository courseRepository;

    /**
     * 새로 생성된 course객체의 정보와 DB에 저장된 값이 동일한지 테스트
     */
    @Test
    public void givenCreateCourseWhenLoadTheCourseThenExpectSameCourse() {

        Course course = new Course("Rapid Spring Boot Application Development", "Spring", 4, "'Spring Boot gives all the power of the Spring Framework without all of the complexities");
        Course savedCourse = courseRepository.save(course);
        assertThat(courseRepository.findById(savedCourse.getId()).get()).isEqualTo(course);
    }

    /**
     * rating의 값을 수정하고 값이 수정 됐는지 테스트
     */
    @Test
    public void givenUpdateCourseWhenLoadTheCourseThenExpectUpdatedCourse(){
        Course course = new Course("Rapid Spring Boot Application Development", "Spring", 4, "'Spring Boot gives all the power of the Spring Framework without all of the complexities");
        courseRepository.save(course);
        course.setRating(5);
        Course savedCourse = courseRepository.save(course);
        assertThat(courseRepository.findById(savedCourse.getId()).get().getRating()).isEqualTo(5);
    }

    /**
     * DB 저장 후 삭제한 다음 조회했을때 조회되지 않는지 테스트
     */
    @Test
    public void givenDeleteCourseWhenLoadTheCourseThenExpectNoCourse(){
        Course course = new Course("Rapid Spring Boot Application Development", "Spring", 4, "'Spring Boot gives all the power of the Spring Framework without all of the complexities");
        Course savedCourse  = courseRepository.save(course);
        assertThat(courseRepository.findById(savedCourse.getId()).get()).isEqualTo(course);
        courseRepository.delete(course);
        assertThat(courseRepository.findById(savedCourse.getId()).isPresent()).isFalse();
    }
}
