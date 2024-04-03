package com.manning.sbip.ch03;

import com.manning.sbip.ch03.model.Course;
import com.manning.sbip.ch03.repository.CourseRepository;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CourseTrackerSpringBootApplicationTests {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    void givenDataAvailableWhenLoadFirstPageThenGetFiveRecords() {
        /* PageRequest.of()를 사용해 페이지 번호와 한 페이지에 나타낼 데이터의 개수를 지정. ( 페이지 번호 0, 페이지 당 데이터 건수 5) */
        Pageable pageable = PageRequest.of(0,5);
        assertThat(courseRepository.findAll(pageable)).hasSize(5);
        assertThat(pageable.getPageNumber()).isEqualTo(0);

        /* pageable의 next를 사용해서 다음 페이지의 데이터를 조회해 페이지 번호와 데이터 개수를 판정 */
        Pageable nextPageable = pageable.next();
        assertThat(courseRepository.findAll(nextPageable)).hasSize(4);
        assertThat(nextPageable.getPageNumber()).isEqualTo(1);
    }

    /**
     * 이름 기준으로 오름차순 정렬 후 목록의 첫번째 요소를 사용해 정렬이 바르게 동작했는지에 대한 테스트
     */
    @Test
    void givenDataAvailableWhenSortsFirstPageThenGetSortedSData() {
        Pageable pageable = PageRequest.of(0,5, Sort.by(Sort.Order.asc("Name")));
        Condition<Course> sortedFirstCourseCondition = new Condition<Course>() {
            @Override
            public boolean matches(Course course) {
                return course.getId() == 4 && course.getName().equals("Cloud Native Spring Boot Application Development");
            }
        };
        assertThat(courseRepository.findAll(pageable)).first().has(sortedFirstCourseCondition);
    }

    /**
     * 평점 기준 내림차순, 이름 기준 오름차순으로 정렬한 후 목록의 첫번째 요소를 사용해 정렬이 바르게 동작했는지에 대한 테스트.
     */
    @Test
    void givenDataAvailableWhenApplyCustomSortThenGetSortedResult() {
        Pageable customSortPageable = PageRequest.of(0,5, Sort.by("Rating").descending().and(Sort.by("Name")));
        Condition<Course> customSortFirstCourseCondition = new Condition<Course>() {
            @Override
            public boolean matches(Course course) {
                return course.getId() == 2 && course.getName().equals("Getting Started with Spring Security DSL");
            }
        };
        assertThat(courseRepository.findAll(customSortPageable)).first().has(customSortFirstCourseCondition);
    }
}
