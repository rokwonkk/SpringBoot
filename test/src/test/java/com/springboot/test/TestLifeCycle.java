package com.springboot.test;

import org.junit.jupiter.api.*;

public class TestLifeCycle {

    @BeforeAll // @BeforeAll -> 테스트를 시작하기 전에 호출되는 메서드를 정의
    static void beforeAll(){
        System.out.println("## BeforeAll Annotation 호출 ##");
        System.out.println();
    }

    @AfterAll // @AfterAll -> 테스트를 종료하면서 메서드를 정의
    static void afterAll(){
        System.out.println("## AfterAll Annotation 호출 ##");
        System.out.println();
    }

    @BeforeEach // @BeforeEach -> 각 테스트 메서드가 실행되기 전에 동작하는 메서드를 정의
    void beforeEach(){
        System.out.println("## BeforeEach Annotation 호출 ##");
        System.out.println();
    }

    @AfterEach // @AfterEach -> 각 테스트 메서드가 종료되면서 호출되는 메서드를 정의
    void afterEach(){
        System.out.println("## AfterEach Annotation 호출 ##");
        System.out.println();
    }

    @Test // @Test -> 테스트 코드를 포함한 메서드를 정의
    void test1(){
        System.out.println("## test1 시작");
        System.out.println();
    }
    @Test
    @DisplayName("Test Case 2!!!")
    void test2(){
        System.out.println("## test2 시작");
        System.out.println();
    }

    @Test
    @Disabled()
    void test3(){
        System.out.println("## test3 시작");
        System.out.println();
    }


}
