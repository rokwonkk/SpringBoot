package com.manning.sbip.ch02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootAppDemoApplication {

	/**
	 * 부트는 기본적으로 다음 위치에 있는 application.properties 파일이나 application.yml 파일을 읽는다.
	 * 1. 클래스패스 루트
	 * 2. 클래스패스 /config 패키지
	 * 3. 현재 디렉터리
	 * 4. 현재 디렉터리 /config 디렉터리
	 * 5. /config 디렉터리의 바로 하위 디렉터리
	 */

	/**
	 * 설정 파일은 다음 순서로 로딩된다.
	 * 1. 애플리케이션 JAR 파일 안에 패키징 되는 application.properties(or .yml)파일
	 * 2. 애플리케이션 JAR 파일 안에 패키징 되는 application-{profile}.properties(or .yml)파일
	 * 3. 애플리케이션 JAR 파일 밖에 패키징 되는 application.properties(or .yml)파일
	 * 4. 애플리케이션 JAR 파일 밖에 패키징 되는 application-{profile}.properties(or .yml)파일
	 */
	public static void main(String[] args) {		
		SpringApplication.run(SpringBootAppDemoApplication.class, args);
	}
}
