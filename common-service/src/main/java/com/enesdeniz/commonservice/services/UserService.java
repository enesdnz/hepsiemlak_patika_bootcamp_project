package com.enesdeniz.commonservice.services;

import com.enesdeniz.commonservice.dto.request.UserRequest;
import com.enesdeniz.commonservice.dto.response.UserResponse;
import com.enesdeniz.commonservice.repository.UserRepository;
import com.enesdeniz.commonservice.services.baseServices.UserBaseService;
import com.enesdeniz.commonservice.core.ErrorResult;
import com.enesdeniz.commonservice.core.Result;
import com.enesdeniz.commonservice.core.SuccessResult;
import com.enesdeniz.commonservice.entities.User;
import com.enesdeniz.commonservice.services.interfaces.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserService extends UserBaseService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserResponse> getAllUser() {

        log.info("get all users from DB");
        List<User> users = userRepository.findAll();
        return users.stream().map(this::convertToUserResponse).collect(Collectors.toList());
    }

    @Override
    public UserResponse getUserByUserId(Long userId) {

        log.info("Get one user from Db -> id: "+userId);

        Optional<User> user = userRepository.findById(userId);

        if(user.isPresent())
            return convertToUserResponse(user.get());
        else
            return new UserResponse();
    }

    @Override
    public Result saveUser(UserRequest userRequest) {
        User user = userRepository.save(convertToUserEntity(userRequest));
        log.info(user  + " adding to DB");
        return new SuccessResult("This user saved successfully");
    }

    @Override
    public Result deleteUser(Long userId){

        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent())
        {
            userRepository.deleteById(userId);
            log.info("Deleted user ->  id: "+ userId );
            return new SuccessResult( "User " + userId + " removed successfully");
        }
        else{
            log.error("No such user found");
            return new ErrorResult(false, "No such user found");
        }

    }

    @Override
    public Result updateUser(Long userId, UserRequest request) {
        try {
            User user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
            user.setName(request.getName());
            user.setUserType(request.getUserType());
            user.setEmail(request.getEmail());
            userRepository.save(user);

            log.info("Update user -> id: "+ userId + " and "+ user);
            return new SuccessResult("The user updated");
        } catch (EntityNotFoundException e) {
            log.error("User does not exists");
            return new ErrorResult(false,"This user does not exists");
        }
    }
}
