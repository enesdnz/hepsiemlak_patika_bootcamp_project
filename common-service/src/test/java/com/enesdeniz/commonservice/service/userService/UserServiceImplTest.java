package com.enesdeniz.commonservice.service.userService;

import com.enesdeniz.commonservice.dto.request.UserRequest;
import com.enesdeniz.commonservice.dto.response.UserResponse;
import com.enesdeniz.commonservice.entities.User;
import com.enesdeniz.commonservice.enums.UserType;
import com.enesdeniz.commonservice.repository.UserRepository;
import com.enesdeniz.commonservice.services.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * this class tests the user service for the common microservice
 */

@SpringBootTest
class UserServiceImplTest extends UserTestSupport{

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;


    @Test
    @DisplayName("A new user should be added as a result of the test")
    void addUserTest(){
        User user = User.builder()
            .userType(UserType.INDIVIDUAL)
            .name("Enes Deniz")
            .email("enodeniz190@gmail.com")
            .password("12345")
            .build();

        UserRequest userRequest = convertToUserRequest(user);

        /*when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

        UserResponse userSaved = userService.saveUser(userRequest);

        assertEquals(userSaved.getUserId(), user.getId());
        assertEquals(userSaved.getEmail(),user.getEmail());
        assertEquals(userSaved.getUserType(),user.getUserType());
        assertEquals(userSaved.getName(),user.getName());*/

        userService.saveUser(userRequest);

        Mockito.verify(userRepository).save(any());

    }

    @Test
    @DisplayName("As a result of the test, all users registered in db should be returned")
    void getAllUsersTest(){
        User testUser1 = new User(UserType.INDIVIDUAL,"Hatice Deniz", "haticedeniz@gmail.com", "12345");
        User testUser2 = new User(UserType.INDIVIDUAL,"Sadettin Deniz", "sadettindeniz@gmail.com", "12345");
        User testUser3 = new User(UserType.CORPORATE,"Selen Deniz", "selendeniz@gmail.com", "12345");

        List<User> users= new ArrayList<>();

        users.add(testUser1);
        users.add(testUser2);
        users.add(testUser3);

        Mockito.when(userRepository.findAll()).thenReturn(users);

        List<UserResponse> allUsers = userService.getAllUser();

        assertEquals(users.size(), allUsers.size());
        verify(userRepository).findAll();
    }


    @Test
    @DisplayName("The user object created as a result of the test should be deleted successfully")
    void deleteUserTest(){

        User testUser = new User(UserType.INDIVIDUAL,"Enes Deniz", "enodeniz190@gmail.com", "12345");

        userRepository.delete(testUser);

        verify(userRepository, Mockito.times(1)).delete(testUser);

    }

    @Test
    @DisplayName("It tests whether the user is coming according to the given id")
    void getUserByUserIdTest(){

        User user = User.builder()
            .userType(UserType.INDIVIDUAL)
            .name("Enes Deniz")
            .email("enodeniz190@gmail.com")
            .password("12345")
            .build();

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        UserResponse userResponse  = userService.getUserByUserId(1L);

        assertEquals(userResponse.getUserId(),user.getId());

    }

    @Test
    @DisplayName("user should be updated as a result of testing")
    public void updateUserTest(){
        // Given
        User user = prepareUserForTest();

        when(userRepository.findById(1L))
            .thenReturn(Optional.ofNullable(user));

        // When
        UserRequest userRequest = new UserRequest();
        userRequest.setUserType(UserType.CORPORATE);

        userService.updateUser(1L, userRequest);

        // Then
        assert user != null;
        assertThat(user.getUserType())
            .isEqualTo(UserType.CORPORATE);
    }

}
