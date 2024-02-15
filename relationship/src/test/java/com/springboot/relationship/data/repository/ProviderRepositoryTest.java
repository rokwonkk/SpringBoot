package com.springboot.relationship.data.repository;

import com.springboot.relationship.data.entity.Product;
import com.springboot.relationship.data.entity.Provider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

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

        Product product1 = new Product();
        product1.setName("레고부품");
        product1.setPrice(1000);
        product1.setStock(500);
        product1.setProvider(provider);


        Product product2 = new Product();
        product2.setName("기계부품");
        product2.setPrice(2000);
        product2.setStock(100);
        product2.setProvider(provider);

        Product product3 = new Product();
        product3.setName("자동차부품");
        product3.setPrice(3000);
        product3.setStock(1000);
        product3.setProvider(provider);

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);

        List<Product> products = providerRepository.findById(provider.getId()).get().getProductList();

        for (Product product : products) {
            System.out.println(product);
        }

//        //테스트
//        System.out.println(
//                "product : " + productRepository.findById(1L)
//                        .orElseThrow(RuntimeException::new)
//        );
//        System.out.println(
//                "provider : " + productRepository.findById(1L)
//                        .orElseThrow(RuntimeException::new).getProvider()
//        );
    }
}