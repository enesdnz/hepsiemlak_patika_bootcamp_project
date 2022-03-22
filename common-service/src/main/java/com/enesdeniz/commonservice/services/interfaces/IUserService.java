package com.enesdeniz.commonservice.services.interfaces;

import com.enesdeniz.commonservice.core.Result;
import com.enesdeniz.commonservice.dto.request.UserRequest;
import com.enesdeniz.commonservice.dto.response.UserResponse;

import java.util.List;

public interface IUserService {

    List<UserResponse> getAllUser();

    UserResponse getUserByUserId(Long userId);

    Result saveUser(UserRequest userRequest);

    Result deleteUser(Long userId);

    Result updateUser(Long userId, UserRequest request);
}
