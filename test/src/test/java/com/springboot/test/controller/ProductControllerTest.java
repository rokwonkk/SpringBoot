package com.springboot.test.controller;

import com.google.gson.Gson;
import com.springboot.test.data.dto.ProductDto;
import com.springboot.test.data.dto.ProductResponseDto;
import com.springboot.test.service.Impl.ProductServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @WebMvcTest를 사용한 테스트는 슬라이스 테스트라고 부름
 * 슬라이스 테스트 = 단위 테스트와 통합 테스트의 중간 개념
 * 레이어드 아키텍처를 기준으로 각 레이어 별로 나누어 테스트 진행
 * 단위 테스트는 모든 외부 요인을 차단하고 테스트 진행하지만
 * 컨트롤러는 웹과 맞닿은 레이어라 외부 요인을 차단하면 테스트 의미가 없음
 * 그렇기에 슬라이스 테스트를 진행하는 경우가 많음
 */
@WebMvcTest(ProductController.class) // @WebMvcTest -> @SpringBootTest보다 가볍게 테스트하기 위해 사용
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean // @MockBean -> 실제 빈 객체가 아닌 Mock(가짜) 객체를 생성, 주입해주는 역할
    ProductServiceImpl productService;

    @Test
    @DisplayName("MockMvc를 통한 Product 데이터 가져오기 테스트")
    void getProductTest() throws Exception {

        /**
         *  given() -> 메서드 호출 및 파라미터 주입
         *  willReturn() -> 결과를 리턴할 구조 정의
         */
        given(productService.getProduct(123L)).willReturn(
                new ProductResponseDto(123L, "pen", 5000, 2000)
        );

        String productId = "123";

        /**
         *  perform() -> URL 요청을 보내는 것 처럼 컨트롤러 테스트 가능
         *  andExpect() -> 결과값 검증 수행
         *  andDo() -> 요청 및 응답 전체 내용 확인
         */
        mockMvc.perform(get("/product?number=" + productId))
                .andExpect(status().isOk())
                .andExpect(jsonPath(
                        "$.number").exists())
                .andExpect(jsonPath(
                        "$.name").exists())
                .andExpect(jsonPath(
                        "$.price").exists())
                .andExpect(jsonPath(
                        "$.stock").exists())
                .andDo(print());

        /**
         *  verify() -> 메서드가 실행됐는지 검증, given()에 대응
         */
        verify(productService).getProduct(123L);
    }

    @Test
    @DisplayName("Product 데이터 생성 테스트")
    void createProductTest() throws Exception {

        //Mock 객체에서 특정 메서드가 실행되는 경우 실제 Return을 줄 수 없기 때문에 가정 사항을 만들어줌
        given(productService.saveProduct(new ProductDto("pen", 5000, 2000)))
                .willReturn(new ProductResponseDto(12315L, "pen", 5000, 2000));

        ProductDto productDto = ProductDto.builder()
                .name("pen")
                .price(5000)
                .stock(2000)
                .build();


        Gson gson = new Gson();
        String content = gson.toJson(productDto);

        mockMvc.perform(
                        post("/product")
                                .content(content)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.number").exists())
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.price").exists())
                .andExpect(jsonPath("$.stock").exists())
                .andDo(print());

        verify(productService).saveProduct(new ProductDto("pen", 5000, 2000));
    }
}