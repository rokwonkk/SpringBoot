package com.springboot.relationship.data.repository;

import com.springboot.relationship.data.entity.Category;
import com.springboot.relationship.data.entity.Product;
import com.springboot.relationship.data.repository.support.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoryRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    void relationshipTest(){
        //테스트 데이터
        Product product = new Product();
        product.setName("노트");
        product.setPrice(500);
        product.setStock(10);

        productRepository.save(product);

        Category category = new Category();
        category.setCode("X1");
        category.setName("도서");
        category.getProducts().add(product);

        categoryRepository.save(category);

        //테스트 코드
        List<Product> products = categoryRepository.findById(1L).get().getProducts();

        for (Product foundProduct : products) {
            System.out.println(product);
        }
    }
}