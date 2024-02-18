package com.springboot.relationship.data.repository;

import com.springboot.relationship.data.entity.Product;
import com.springboot.relationship.data.entity.Provider;
import org.assertj.core.util.Lists;
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

    @Test
    void cascadeTest(){
        Provider provider = savedProvider("새로운 래고공장");

        Product product1 = savedProduct("래고1", 1000, 1000);
        Product product2 = savedProduct("래고2", 500, 1500);
        Product product3 = savedProduct("래고3", 750, 500);

        //연관관계 설정
        product1.setProvider(provider);
        product2.setProvider(provider);
        product3.setProvider(provider);

        provider.getProductList().addAll(Lists.newArrayList(product1, product2, product3));

        providerRepository.save(provider);
    }

    private Provider savedProvider(String name){
        Provider provider = new Provider();
        provider.setName(name);

        return provider;
    }

    private Product savedProduct(String name, Integer price, Integer stock){
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setStock(stock);

        return product;
    }
}
