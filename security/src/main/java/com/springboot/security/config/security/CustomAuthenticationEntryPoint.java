package com.springboot.security.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.security.data.dto.EntryPointErrorResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.boot.model.internal.CreateKeySecondPass;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

//    @Override
//    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        log.info("[commence] 인증 실패로 response.sendError 발생");
//
//        EntryPointErrorResponse entryPointErrorResponse = new EntryPointErrorResponse();
//        entryPointErrorResponse.setMsg("인증이 실패하였습니다.");
//
//        response.setStatus(401);
//        response.setContentType("application/json");
//        response.setCharacterEncoding("utf-8");
//        response.getWriter().write(objectMapper.writeValueAsString(entryPointErrorResponse));
//    }

    //위와 같이 굳이 메시지 설정 필요없으면 인증 실패 코드만 전달해도 됨.
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
