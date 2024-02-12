package com.springboot.advanced_jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//JPA Auditing 활성화 - 데이터 생성 변경의 감시

@SpringBootApplication
@EnableJpaAuditing
public class AdvancedJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdvancedJpaApplication.class, args);
	}

}
