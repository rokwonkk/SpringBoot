package com.manning.sbip.ch02.configurationproperties;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.boot.context.properties.bind.DefaultValue;

@ConstructorBinding
@ConfigurationProperties("app.sbip.ct")
public class AppProperties {
	
	/**
	 * Application Name
	 */
	private final String name; //애플리케이션 이름
	
	/**
	 * Application IP
	 */
	private final String ip; //애플리케이션 IP

	/**
	 * Application PORT
	 */
	private final int port; //애플리케이션 포트
	
	/**
	 * Application Security configuration
	 */
	private final Security security; //애플리케이션 보안 설정

	public String getName() {
		return name;
	}

	public String getIp() {
		return ip;
	}

	public int getPort() {
		return port;
	}

	public Security getSecurity() {
		return security;
	}

	//@DefaultValue를 사용하면 프로퍼티 기본값을 지정할 수 있다.
	public AppProperties(String name, String ip, @DefaultValue("8080") int port, Security security) {
		this.name = name;
		this.ip = ip;
		this.port = port;
		this.security = security;
	}

	@Override
	public String toString() {
		return "AppProperties{" +
				"name='" + name + '\'' +
				", ip='" + ip + '\'' +
				", port='" + port + '\'' +
				", security=" + security +
				'}';
	}

	public static class Security {
		
		/**
		 * Enable Security. Possible values true/false
		 */
		private boolean enabled; // 보안 활성화
		
		/**
		 * Token Value
		 */
		private final String token; //토큰 값
		
		/**
		 * Available roles
		 */
		private final List<String> roles; //역할
		
		public Security(boolean enabled, String token, List<String> roles) {
			this.enabled = enabled;
			this.token = token;
			this.roles = roles;
		}

		public boolean isEnabled() {
			return enabled;
		}

		public String getToken() {
			return token;
		}

		public List<String> getRoles() {
			return roles;
		}

		@Override
		public String toString() {
			return "Security{" +
					"enabled=" + enabled +
					", token='" + token + '\'' +
					", roles=" + roles +
					'}';
		}
	}

}
