package com.springboot.login_api.controller;

import com.springboot.login_api.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/login/oauth2", produces = "application/json")
public class SocialLoginController {

    @Autowired
    private LoginService service;

    @GetMapping("/code/{registrationId}")
    public void socialLogin(@RequestParam String code, @PathVariable String registrationId) {
    	
    	service.socialLogin(code, registrationId);
    	
//    	if(registrationId.equals("google")) {
//    		System.out.println("구글 로그인");
//    		service.socialLogin(code, registrationId);	
//    	}else if(registrationId.equals("kakao")) {
//    		System.out.println("카카오 로그인");
//    		service.socialLogin(code, registrationId);
//    	}
    }
}
