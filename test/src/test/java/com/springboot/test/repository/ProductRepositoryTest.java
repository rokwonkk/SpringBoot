package com.springboot.test.repository;

import com.springboot.test.data.entity.Product;
import com.springboot.test.data.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@AutoConfigureTestDatabase -> 어노테이션 값을 조정하는 작업을 수행
//replace 속성의 기본값은 Replace.ANY이며, 이 때 임베디드 메모리 데이터베이스를 사용함.
//Replace.NONE로 변경하면 애플리케이션에서 실제 사용하는 DB로 테스트 가능.
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void save(){
        Product product = new Product();
        product.setName("펜");
        product.setPrice(1000);
        product.setStock(1000);

        Product savedProduct = productRepository.saveAndFlush(product);

        assertEquals(product.getName(), savedProduct.getName());
        assertEquals(product.getPrice(), savedProduct.getPrice());
        assertEquals(product.getStock(), savedProduct.getStock());
    }
}
