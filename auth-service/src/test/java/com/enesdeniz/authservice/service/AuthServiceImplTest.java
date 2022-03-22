package com.enesdeniz.authservice.service;

import com.enesdeniz.authservice.dto.AuthRequest;
import com.enesdeniz.authservice.dto.AuthResponse;
import com.enesdeniz.authservice.entity.User;
import com.enesdeniz.authservice.repository.AuthRepository;
import com.enesdeniz.authservice.util.JwtUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * this class tests the auth service for the auth microservice
 */

@SpringBootTest
public class AuthServiceImplTest {

    @InjectMocks
    private AuthService authService;

    @Mock
    private AuthRepository authRepository;

    @Mock
    private JwtUtil jwtUtil;

    @Test
    @DisplayName("test result should generate token")
    void getTokenTest() throws Exception {

        AuthRequest request = prepareAuthRequest("215215");
        String token = "token1234";
        User user = prepareUser("enodeniz190@gmail.com");

        Mockito
            .when(authRepository.findByEmail(request.getEmail()))
            .thenReturn(Optional.ofNullable(user));

        assert user != null;
        Mockito
            .when(jwtUtil.generateToken(user))
            .thenReturn(token);

        assertDoesNotThrow(() -> {
            AuthResponse response = authService.getToken(request);
            assertNotNull(response);
            assertEquals("token1234", response.getToken());


        });


    }

    @Test
    @DisplayName("Checking if the token created in the test is null")
    void getTokenUserIsNullTest() throws Exception {

        AuthRequest request = prepareAuthRequest("215215");
        String token = "token1234";
        User user = prepareUser("enodeniz190@gmail.com");

        Mockito
            .when(authRepository.findByEmail(request.getEmail()))
            .thenReturn(null);

        Mockito
            .when(jwtUtil.generateToken(user))
            .thenReturn(token);

        assertThrows(Exception.class,() -> {
            AuthResponse response = authService.getToken(request);


        },"User is null");


    }

    @Test
    @DisplayName("Checking if the password is correct when the token is created")
    void getTokenPasswordIsIncorrectTest() throws Exception {

        // create authrequest object
        AuthRequest request = prepareAuthRequest("215216");
        String token = "token1234";
        User user = prepareUser("enodeniz190@gmail.com");

        Mockito
            .when(authRepository.findByEmail(request.getEmail()))
            .thenReturn(Optional.ofNullable(user));

        // user null control
        assert user != null;
        Mockito
            .when(jwtUtil.generateToken(user))
            .thenReturn(token);

        assertThrows(Exception.class,() -> {
            AuthResponse response = authService.getToken(request);
            assertNotEquals(request.getPassword(),user.getPassword());
        },"Password is incorrect");


    }

    // we create user for tests
    private User prepareUser(String email) {

        return new User().builder().id(1).email(email).password("215215").build();

    }

    // we create auth requests for tests
    private AuthRequest prepareAuthRequest(String password) {

        return new AuthRequest("enodeniz190@gmail.com",password);
    }
}
