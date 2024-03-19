package com.springboot.login_api.service;

import com.springboot.login_api.dao.SnsLoginDao;
import com.springboot.login_api.dto.SnsLoginDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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

    private final SnsLoginDao dao;
    private final Environment env;
    private final RestTemplate restTemplate = new RestTemplate();
    public LoginService(Environment env, SnsLoginDao dao) {
        this.env = env;
        this.dao = dao;
    }

    public SnsLoginDto socialLogin(String code, String registrationId, HttpServletRequest req) {
        System.out.println("code = " + code);
        System.out.println("registrationId = " + registrationId);

        String accessToken = getAccessToken(code, registrationId);
        System.out.println("accessToken = " + accessToken);

        JsonNode userResourceNode = getUserResource(accessToken, registrationId);
        System.out.println("userResourceNode = " + userResourceNode);

        SnsLoginDto snsLogin = null;
        String id = "";
        String email = "";
        switch (registrationId) {
            case "google" -> {
                System.out.println("구글 로그인");

                id = userResourceNode.get("id").asText();
                email = userResourceNode.get("email").asText();

                System.out.println("id = " + id);
                System.out.println("email = " + email);

                snsLogin = new SnsLoginDto(id, email, "google");

                int findGoogleId = dao.findBySnsId(snsLogin);
                if (findGoogleId == 0) {
                    System.out.println("구글 엄슴");
                    dao.insertSnsId(snsLogin);
                }

            }
            case "kakao" -> {
                System.out.println("카카오 로그인");

                id = userResourceNode.get("id").asText();
                email = userResourceNode.get("kakao_account").get("email").asText();

                System.out.println("id = " + id);
                System.out.println("email = " + email);

                snsLogin = new SnsLoginDto(id, email, "kakao");

                int findGoogleId = dao.findBySnsId(snsLogin);
                if (findGoogleId == 0) {
                    System.out.println("카카오 계정이 존재하지 않습니다.");
                    dao.insertSnsId(snsLogin);
                } else {
                    System.out.println("카카오 계정이 존재합니다.");
                }
            }
            case "naver" -> {
                System.out.println("네이버 로그인");

                id = userResourceNode.get("response").get("id").asText();
                email = userResourceNode.get("response").get("email").asText();

                System.out.println("id = " + id);
                System.out.println("email = " + email);

                snsLogin = new SnsLoginDto(id, email, "naver");

                int findGoogleId = dao.findBySnsId(snsLogin);
                if (findGoogleId == 0) {
                    System.out.println("네이버 계정이 존재하지 않습니다.");
                    dao.insertSnsId(snsLogin);
                } else {
                    System.out.println("네이버 계정이 존재합니다.");
                }
            }
        }
        //세션이 있으면 있는 세션 반환, 없으면 신규 세션 생성
        HttpSession session = req.getSession();

        //세션에 로그인 회원 담자
        session.setAttribute("id", id);
        session.setAttribute("email", email);

        System.out.println(session.getAttribute("id"));
        System.out.println(session.getAttribute("email"));

        return snsLogin;
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
        String resourceUri = env.getProperty("oauth2." + registrationId + ".resource-uri");

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        HttpEntity entity = new HttpEntity(headers);
        return restTemplate.exchange(Objects.requireNonNull(resourceUri), HttpMethod.GET, entity, JsonNode.class).getBody();
    }
}
