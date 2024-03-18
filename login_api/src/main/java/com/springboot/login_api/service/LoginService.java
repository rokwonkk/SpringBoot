package com.springboot.login_api.service;

import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.Objects;

@Service
public class LoginService {
    
	private final Environment env;
    private final RestTemplate restTemplate = new RestTemplate();

	public LoginService(Environment env) {
        this.env = env;
    }
	
	public void socialLogin(String code, String registrationId) {
		System.out.println("code = " + code);
		System.out.println("registrationId = " + registrationId);
		
        String accessToken = getAccessToken(code, registrationId);
        System.out.println("accessToken = " + accessToken);
        
        JsonNode userResourceNode = getUserResource(accessToken, registrationId);
        System.out.println("userResourceNode = " + userResourceNode);

        switch (registrationId) {
            case "google" -> {
                System.out.println("구글 로그인");

                String id = userResourceNode.get("id").asText();
                String email = userResourceNode.get("email").asText();

                System.out.println("id = " + id);
                System.out.println("email = " + email);
            }
            case "kakao" -> {
                System.out.println("카카오 로그인");

                String id = userResourceNode.get("id").asText();
                String email = userResourceNode.get("kakao_account").get("email").asText();

                System.out.println("id = " + id);
                System.out.println("email = " + email);
            }
            case "naver" -> {
                System.out.println("네이버 로그인");

                String id = userResourceNode.get("response").get("id").asText();
                String email = userResourceNode.get("response").get("email").asText();

                System.out.println("id = " + id);
                System.out.println("email = " + email);
            }
        }
	}

    private String getAccessToken(String authorizationCode, String registrationId) {
        String clientId = env.getProperty("oauth2." + registrationId + ".client-id");
        String clientSecret = env.getProperty("oauth2." + registrationId + ".client-secret");
        String redirectUri = env.getProperty("oauth2." + registrationId + ".redirect-uri");
        String tokenUri = env.getProperty("oauth2." + registrationId + ".token-uri");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("code", authorizationCode);
        params.add("client_id", clientId);
        params.add("client_secret", clientSecret);
        params.add("redirect_uri", redirectUri);
        params.add("grant_type", "authorization_code");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity entity = new HttpEntity(params, headers);

        ResponseEntity<JsonNode> responseNode = restTemplate.exchange(Objects.requireNonNull(tokenUri), HttpMethod.POST, entity, JsonNode.class);
        JsonNode accessTokenNode = responseNode.getBody();
        return Objects.requireNonNull(accessTokenNode).get("access_token").asText();
    }

    private JsonNode getUserResource(String accessToken, String registrationId) {
        String resourceUri = env.getProperty("oauth2."+registrationId+".resource-uri");

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        HttpEntity entity = new HttpEntity(headers);
        return restTemplate.exchange(Objects.requireNonNull(resourceUri), HttpMethod.GET, entity, JsonNode.class).getBody();
    }
}
