package com.springboot.jpa.service.Impl;


import com.springboot.jpa.data.repository.ProductRepository;
import com.springboot.jpa.service.ProductService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

// 객체를 주입받기 위해서 @ExtendWith(SpringExtension.class) 사용해 JUnit5의 테스트에서 스프링 테스트 컨텍스트를 사용하도록 설정
@ExtendWith(SpringExtension.class)
//ProductService를 주입받기 위해 @Import 사용
@Import({ProductServiceImpl.class})
class ProductServiceTest2 {

    @MockBean
    ProductRepository productRepository;

    @Autowired
    ProductService productService;


}