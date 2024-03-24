package com.springboot.security.config.security;

import com.springboot.security.config.JwtTokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * 부트에선 필터를 여러 방법으로 구현할 수 있는데,
 * 편한 구형 방법인 필터를 상속받아서 사용하는 것,
 * 대표적 많이 사용되는 상속 객체는 GenericFilterBean, OncePerRequestFilter
 */
//1. OncePerRequestFilter로 구현
//@Slf4j
//public class JwtAuthenticationFilter extends OncePerRequestFilter {
//
//    private final JwtTokenProvider jwtTokenProvider;
//
//    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider){
//        this.jwtTokenProvider = jwtTokenProvider;
//    }
//
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//
//        String token = jwtTokenProvider.resolveToken(request);
//        log.info("[doFilterInternal] token 값 추출 완료, token : {}", token);
//
//        log.info("[doFilterInternal token 값 유효성 체크 시작]");
//        if (token != null && jwtTokenProvider.validateToken(token)){
//            Authentication authentication = jwtTokenProvider.getAuthentication(token);
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//            log.info("[doFilterInternal token 값 유효성 체크 완료]");
//        }
//
//        filterChain.doFilter(request, response);
//    }
//}

//2. GenericFilterBean로 구현
@Slf4j
public class JwtAuthenticationFilter extends GenericFilterBean {

    private final JwtTokenProvider jwtTokenProvider;

    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //jwtTokenProvider 통해 ServletRequest에서 토큰을 추출함
        String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);
        log.info("[doFilterInternal] token 값 추출 완료. token : {}", token);

        log.info("[doFilterInternal] token 값 유효성 체크 시작");
        //ServletRequest에서 추출한 토큰의 유효성을 검사함.
        if (token != null && jwtTokenProvider.validateToken(token)) {
            Authentication authentication = jwtTokenProvider.getAuthentication(token);
            // 유효하면 SecurityContextHolder에 추가하는 작업함.
            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.info("[doFilterInternal token 값 유효성 체크 완료]");
        }
        chain.doFilter(request, response);
    }
}