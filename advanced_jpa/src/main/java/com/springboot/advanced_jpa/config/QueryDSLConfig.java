package com.springboot.advanced_jpa.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueryDSLConfig {

    @PersistenceContext
    EntityManager em;

    /**
     *  사용할 때마다 매번 JPAQeryFactory를 초기화 하기 귀찮아서
     *  스프링 컨테이너에 등록해서 바로바로 쓸 수 있게 설정함.
     */
    @Bean
    public JPAQueryFactory jpaQueryFactory(){
        return new JPAQueryFactory(em);
    }
}
