package com.enesdeniz.commonservice.services.baseServices;

import com.enesdeniz.commonservice.dto.request.UserRequest;
import com.enesdeniz.commonservice.dto.response.UserResponse;
import com.enesdeniz.commonservice.entities.User;

public class UserBaseService {

    protected User convertToUserEntity(UserRequest userRequest) {
        User user = new User();
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setUserType(userRequest.getUserType());
        return user;
    }

    protected UserResponse convertToUserResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setUserId(user.getId());
        userResponse.setName(user.getName());
        userResponse.setEmail(user.getEmail());
        userResponse.setUserType(user.getUserType());
        return userResponse;
    }
}
