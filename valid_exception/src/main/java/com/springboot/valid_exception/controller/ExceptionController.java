package com.springboot.valid_exception.controller;

import com.springboot.valid_exception.common.Constants;
import com.springboot.valid_exception.common.exception.CustomException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.flogger.Flogger;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.ILoggerFactory;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/exception")
public class ExceptionController {

    @GetMapping
    public void getRuntimeException(){
        throw new RuntimeException("getRuntimeException 호출");
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleException(RuntimeException e, HttpServletRequest req){
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        log.error("클래스 내 handleException 호출, {}, {}", req.getRequestURI(), e.getMessage());

        Map<String, String> map = new HashMap<>();
        map.put("error type", httpStatus.getReasonPhrase());
        map.put("code", "400");
        map.put("message", e.getMessage());

        return new ResponseEntity<>(map, responseHeaders, httpStatus);
    }

    @GetMapping("/custom")
    public void getCustomException() throws CustomException{
        throw new CustomException(Constants.ExceptionClass.PRODUCT, HttpStatus.BAD_REQUEST, "getCustomException 메서드 호출");
    }
}
