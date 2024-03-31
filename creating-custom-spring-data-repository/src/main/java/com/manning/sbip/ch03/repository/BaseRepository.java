package com.manning.sbip.ch03.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;
;

/**
 * 커스텀 리포지토리 생성.
 * 스프링 데이터의 Repository 인터페이스를 상속받는 기본 리포지터리 정의
 * CrudRepository의 메서드 중 실제 노출할 CRUD만 확장하면됨.
 * 스프링 데이터가 감지해 구현체가 자동으로 만들어지지 않도록 @NoRepositoryBean을 붙여줌.
 * @param <T>
 * @param <ID>
 */
@NoRepositoryBean
public interface BaseRepository<T, ID> extends Repository<T, ID> {

    <S extends T> S save(S entity);
    Iterable<T> findAll();

}
