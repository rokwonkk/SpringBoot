package com.springboot.relationship.data.repository;

import com.springboot.relationship.data.entity.Product;
import com.springboot.relationship.data.entity.Provider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProviderRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProviderRepository providerRepository;

    @Test
    void relationshipTest1(){
        //테스트 데이터 생성
        Provider provider = new Provider();
        provider.setName("레고공장");

        providerRepository.save(provider);

        Product product = new Product();

        product.setName("레고부품");
        product.setPrice(1000);
        product.setStock(500);
        product.setProvider(provider);

        productRepository.save(product);

        //테스트
        System.out.println(
                "product : " + productRepository.findById(1L)
                        .orElseThrow(RuntimeException::new)
        );
        System.out.println(
                "provider : " + productRepository.findById(1L)
                        .orElseThrow(RuntimeException::new).getProvider()
        );
    }
}