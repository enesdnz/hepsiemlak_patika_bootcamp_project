package com.enesdeniz.authservice.controller;

import com.enesdeniz.authservice.dto.AuthRequest;
import com.enesdeniz.authservice.dto.AuthResponse;
import com.enesdeniz.authservice.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("auth")
public final class AuthController {

	private final AuthService authService;

	@PostMapping
	public ResponseEntity<AuthResponse> getToken(@RequestBody AuthRequest request) {
		return new ResponseEntity<>(authService.getToken(request), HttpStatus.OK);
	}

}
