package com.enesdeniz.authservice.service;

import com.enesdeniz.authservice.dto.AuthRequest;
import com.enesdeniz.authservice.dto.AuthResponse;
import com.enesdeniz.authservice.entity.User;
import com.enesdeniz.authservice.exception.UserNotFoundException;
import com.enesdeniz.authservice.exception.UserPasswordNotValidException;
import com.enesdeniz.authservice.repository.AuthRepository;
import com.enesdeniz.authservice.util.JwtUtil;
import com.enesdeniz.authservice.util.UserUtil;
import org.springframework.stereotype.Service;



import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RequiredArgsConstructor
@Slf4j
@Service
public class AuthService {

	private final AuthRepository authRepository;

	private final JwtUtil jwtUtil;

	public AuthResponse getToken(AuthRequest request) throws UserPasswordNotValidException {
		User user = authRepository.findByEmail(request.getEmail())
				.orElseThrow(() -> new UserNotFoundException("User not found"));


		if (!UserUtil.isValidPassword(user.getPassword(), request.getPassword())) {
			log.error("User's password not valid " + request.getEmail());
			throw new UserPasswordNotValidException("User's password not valid");
		}

		return new AuthResponse(jwtUtil.generateToken(user));
	}

}
