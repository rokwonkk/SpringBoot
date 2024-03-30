package com.manning.sbip.ch03.repository;

import com.manning.sbip.ch03.entity.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @Repository를 붙여서 스프링 리포지터리로 사용된다는 것을 알려줌.
 * 인터페이스 내용이 비어 있더라도 스프링 데이터 JPA가
 * 런타임에 인터페이스 구현체를 자동으로 만들어준다는 점.
 * 인터페이스 내용을 따로 작성하지 않아도 기본적인 CRUD 연산을 수행함.
 */
@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {
}
