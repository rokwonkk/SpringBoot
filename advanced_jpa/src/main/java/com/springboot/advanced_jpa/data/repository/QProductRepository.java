package com.springboot.advanced_jpa.data.repository;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.springboot.advanced_jpa.data.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Optional;

public interface QProductRepository extends JpaRepository<Product, Long>,
        QuerydslPredicateExecutor<Product> {

    @Override
    Optional<Product> findOne(Predicate predicate);

    @Override
    Iterable<Product> findAll(Predicate predicate);

    @Override
    Iterable<Product> findAll(Predicate predicate, Sort sort);

    @Override
    Iterable<Product> findAll(Predicate predicate, OrderSpecifier<?>... orders);

    @Override
    Iterable<Product> findAll(OrderSpecifier<?>... orders);

    @Override
    Page<Product> findAll(Predicate predicate, Pageable pageable);

    @Override
    long count(Predicate predicate);

    @Override
    boolean exists(Predicate predicate);
}
