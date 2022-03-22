package com.enesdeniz.commonservice.service.userService;

import com.enesdeniz.commonservice.dto.request.UserRequest;
import com.enesdeniz.commonservice.entities.User;
import com.enesdeniz.commonservice.enums.UserType;

public class UserTestSupport {

    protected UserRequest convertToUserRequest(User user) {
        UserRequest userRequest = new UserRequest();
        userRequest.setName(user.getName());
        userRequest.setEmail(user.getEmail());
        userRequest.setUserType(user.getUserType());
        return userRequest;
    }

    protected User prepareUserForTest(){
        User user = new User(UserType.INDIVIDUAL, "Enes", "enesdeniz@gmail.com","12345");
        return user;
    }
}
