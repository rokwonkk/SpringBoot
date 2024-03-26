package com.manning.sbip.ch02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * 자바 8 이후로 동일한 애너테이션 여러번 사용 가능.
 * 각각 @PropertySource로 지정해서 사용 가능함.
 */
@Configuration
@PropertySource("classpath:dbConfig.properties")
//@PropertySource("classpath:redisConfig.properties")
public class DbConfiguration {
	
	@Autowired
	private Environment env;
	
	@Override
	public String toString() {
		return "User: "+env.getProperty("user") +", Password: "+env.getProperty("password");
	}

}
