package com.manning.sbip.ch02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

/**
 * 운영 체제 환경 변수로 지정한 값도 설정 정보 파일에서 읽어서 사용 가능.
 * 윈도우 환경에서는 set <VAR>=<value>
 * 리눅스 환경에서는 export <VAR>=<value>
 * application.properties 파일에 지정된 값과 명령행 인자로 전달한 값중 우선순위가 있다.
 * 1. SpringApplication
 * 2. @PropertySource
 * 3. 설정 정보 파일(application.properties)
 * 4. 운영체제 환경 변수
 * 5. 명령행 인자
 */

@SpringBootApplication
public class SpringBootAppDemoApplication {

	private static final Logger log = LoggerFactory.getLogger(SpringBootAppDemoApplication.class);

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringBootAppDemoApplication.class, args);
		Environment env = applicationContext.getBean(Environment.class);
		log.info("Configured application timeout value: "+ env.getProperty("app.timeout"));
	}
}
