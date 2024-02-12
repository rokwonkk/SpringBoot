package com.springboot.advanced_jpa.data.repository;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.springboot.advanced_jpa.data.entity.Product;
import com.springboot.advanced_jpa.data.entity.QProduct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    JPAQueryFactory query;

    @PersistenceContext
    EntityManager em;



    @Test
    void sortingAndPagingTest(){
        Product product1 = new Product();
        product1.setName("펜");
        product1.setPrice(1000);
        product1.setStock(100);
        product1.setCreatedAt(LocalDateTime.now());
        product1.setUpdatedAt(LocalDateTime.now());

        Product product2 = new Product();
        product2.setName("펜");
        product2.setPrice(5000);
        product2.setStock(300);
        product2.setCreatedAt(LocalDateTime.now());
        product2.setUpdatedAt(LocalDateTime.now());

        Product product3 = new Product();
        product3.setName("펜");
        product3.setPrice(500);
        product3.setStock(50);
        product3.setCreatedAt(LocalDateTime.now());
        product3.setUpdatedAt(LocalDateTime.now());

        Product saveProduct1 = productRepository.save(product1);
        Product saveProduct2 = productRepository.save(product2);
        Product saveProduct3 = productRepository.save(product3);

        /** 정렬 처리 **/
        productRepository.findByName("펜", Sort.by(Order.asc("price")));
        productRepository.findByName("펜", getSort());

        /** 페이징 처리 **/
        Page<Product> productPage = productRepository.findByName("펜", PageRequest.of(0, 2));

    }

    private Sort getSort(){
        return Sort.by(
                Order.asc("price"),
                Order.desc("stock")
        );
    }

    @Test
    void queryDslTest(){

        JPAQuery<Product> query = new JPAQuery(em);
        QProduct qProduct = QProduct.product;

        List<Product> productsList = query
                .from(qProduct)
                .where(qProduct.name.eq("펜"))
                .orderBy(qProduct.price.asc())
                .fetch();

        for (Product product : productsList) {

            System.out.println("---------------------------");
            System.out.println();
            System.out.println("Product Number : " + product.getNumber());
            System.out.println("Product Name : " + product.getName());
            System.out.println("Product Price : " + product.getPrice());
            System.out.println("Product Stock : " + product.getStock());
            System.out.println();
            System.out.println("---------------------------");
        }
    }

    @Test
    void queryDslTest2(){

        JPAQueryFactory query = new JPAQueryFactory(em);
        QProduct qProduct = QProduct.product;

        List<Product> productList = query.selectFrom(qProduct)
                .where(qProduct.name.eq("펜"))
                .orderBy(qProduct.price.asc())
                .fetch();

        for (Product product : productList) {

            System.out.println("---------------------------");
            System.out.println();
            System.out.println("Product Number : " + product.getNumber());
            System.out.println("Product Name : " + product.getName());
            System.out.println("Product Price : " + product.getPrice());
            System.out.println("Product Stock : " + product.getStock());
            System.out.println();
            System.out.println("---------------------------");
        }
    }

    @Test
    void queryDslTest3(){
        JPAQueryFactory query = new JPAQueryFactory(em);
        QProduct qProduct = QProduct.product;

        List<String> productList = query
                .select(qProduct.name)
                .from(qProduct)
                .where(qProduct.name.eq("펜"))
                .orderBy(qProduct.price.asc())
                .fetch();

        for (String product : productList) {
            System.out.println();
            System.out.println("Product Name : " + product);
            System.out.println();
        }

        List<Tuple> tupleList = query
                .select(qProduct.name, qProduct.price)
                .from(qProduct)
                .where(qProduct.name.eq("펜"))
                .orderBy(qProduct.price.asc())
                .fetch();

        for (Tuple tuple : tupleList) {
            System.out.println("--------------------------------");
            System.out.println("Product Name : " + tuple.get(qProduct.name));
            System.out.println("Product Name : " + tuple.get(qProduct.price));
            System.out.println("--------------------------------");
        }
    }

    @Test
    @DisplayName("JPAQueryFactory를 스프링 컨테이너에 등록해서 사용")
    void queryDslTest4(){
        QProduct qProduct = QProduct.product;
        List<String> productList = query
                .select(qProduct.name)
                .from(qProduct)
                .where(qProduct.name.eq("펜"))
                .orderBy(qProduct.price.asc())
                .fetch();

        for (String s : productList) {

            System.out.println("---------------------");
            System.out.println("Product Name : " + s);
            System.out.println("---------------------");

        }
    }
}