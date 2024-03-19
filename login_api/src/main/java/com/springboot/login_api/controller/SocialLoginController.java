package com.springboot.login_api.controller;

import com.springboot.login_api.dto.SnsLoginDto;
import com.springboot.login_api.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/login/oauth2", produces = "application/json")
public class SocialLoginController {

    private final LoginService service;

	public SocialLoginController(LoginService service) {
		this.service = service;
	}

	@GetMapping("/code/{registrationId}")
    public SnsLoginDto socialLogin(@RequestParam String code, @PathVariable String registrationId, HttpServletRequest req) {
		return service.socialLogin(code, registrationId, req);
    }
}
