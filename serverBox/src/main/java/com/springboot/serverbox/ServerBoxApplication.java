package com.springboot.serverbox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * RestTemplate

 * 제공 메서드

 * getForObject     ->  GET 형식으로 요청한 결과 객체로 반환
 * getForEntity     ->  GET 형식으로 요청한 결과를 ResponseEntity 결과로 반환
 * postForLocation  ->  POST 형식으로 요청한 결과를 헤더에 저장된 URI로 반환
 * postForObject    ->  POST 형식으로 요청한 결과를 객체로 반환
 * postForEntity    ->  POST 형식으로 요청한 결과를 ResponseEntity 형식으로 반환
 * delete           ->  DELETE 형식으로 요청
 * put              ->  PUT 형식으로 요청
 * patchForObject   ->  PATCH 형식으로 요청한 결과를 객체로 반환
 * optionsForAllow  ->  해당 URI에서 지원하는 HTTP 메서드를 조회
 * exchange         ->  HTTP 헤더를 임의로 추가할 수 있고, 어떤 메서드 형식에서도 사용할 수 있음
 * execute          ->  요청과 응답에 대한 콜백을 수정
 */

@SpringBootApplication
public class ServerBoxApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerBoxApplication.class, args);
    }

}
