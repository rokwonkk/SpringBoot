package com.springboot.test.repository;

import com.springboot.test.data.entity.Product;
import com.springboot.test.data.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest // @DataJpaTest -> 다음과 같은 기능을 제공한다
                                //JPA와 관련된 설정만 로드
                                //기본적으로 Transactional 어노테이션을 포함하고 있어 테스트 코드 종료 시에 자동 롤백
                                //임메디드 DB를 기본 값으로 사용. 다른 DB를 사용하려면 별도 설정해주면 됨.
public class ProductRepositoryByH2Test {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void saveTest(){

        //given
        Product product = new Product();
        product.setName("펜");
        product.setPrice(1000);
        product.setStock(1000);

        //when
        Product savedProduct = productRepository.save(product);

        //then
        assertEquals(product.getName(), savedProduct.getName());
        assertEquals(product.getPrice(), savedProduct.getPrice());
        assertEquals(product.getStock(), savedProduct.getStock());

    }

    @Test
    void selectTest(){
        //조회 테스트를 위해서는 당연히 값을 추가한 상테에서 비교를 해야하기 때문에 save를 해서 데이터를 추가해준다.

        //given -> 데이터를 저장하는 작업 수행
        Product product = new Product();
        product.setName("펜");
        product.setPrice(1000);
        product.setStock(1000);

        Product savedProduct = productRepository.saveAndFlush(product);

        //when -> 조회 메서드 호출
        Product findProduct = productRepository.findById(savedProduct.getNumber()).get();

        //then -> 저장되어있는 데이터와 조회 하는 데이터와 맞는지 검증
        assertEquals(product.getName(), findProduct.getName());
        assertEquals(product.getPrice(), findProduct.getPrice());
        assertEquals(product.getStock(), findProduct.getStock());
    }
}
