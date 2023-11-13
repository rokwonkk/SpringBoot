package com.springboot.test.repository;

import com.springboot.test.data.entity.Product;
import com.springboot.test.data.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest // @DataJpaTest를 사용하지 않고 @SpringBootTest 어노테이션으로도 테스트 가능.
public class ProductRepository2Test {

    @Autowired
    private ProductRepository productRepository;

    // given(초기데이터)를 활용한 CRUD전체 테스트 다만 모든 설정을 가져오고 전체를 스캔하기 때문에
    // 속도가 조금 느리므로 적당한 트레이드오프로 테스트 하는게 좋음
    @Test
    public void basicCRUDTest(){
        /* create */
        //given
        Product givenProduct = Product.builder()
                .name("노트")
                .price(1000)
                .stock(500)
                .build();

        //when
        Product savedProduct = productRepository.save(givenProduct);

        assertThat(savedProduct.getNumber()).isEqualTo(givenProduct.getNumber());
        assertThat(savedProduct.getName()).isEqualTo(givenProduct.getName());
        assertThat(savedProduct.getPrice()).isEqualTo(givenProduct.getPrice());
        assertThat(savedProduct.getStock()).isEqualTo(givenProduct.getStock());

        /* read */
        //when
        Product selectedProduct = productRepository.findById(savedProduct.getNumber()).orElseThrow(RuntimeException::new);

        //then
        assertThat(selectedProduct.getNumber()).isEqualTo(givenProduct.getNumber());
        assertThat(selectedProduct.getName()).isEqualTo(givenProduct.getName());
        assertThat(selectedProduct.getPrice()).isEqualTo(givenProduct.getPrice());
        assertThat(selectedProduct.getStock()).isEqualTo(givenProduct.getStock());

        /* update */
        Product findProduct = productRepository.findById(savedProduct.getNumber()).orElseThrow(RuntimeException::new);

        findProduct.setName("장난감");

        Product updateProduct = productRepository.save(findProduct);

        //then
        assertEquals(updateProduct.getName(), "장난감");

        /* delete */
        //when
        productRepository.delete(updateProduct);

        //then
        assertFalse(productRepository.findById(selectedProduct.getNumber()).isPresent());
    }
}
