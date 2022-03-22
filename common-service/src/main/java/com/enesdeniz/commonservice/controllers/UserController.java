package com.enesdeniz.commonservice.controllers;

import com.enesdeniz.commonservice.core.Result;
import com.enesdeniz.commonservice.dto.request.UserRequest;
import com.enesdeniz.commonservice.dto.response.UserResponse;
import com.enesdeniz.commonservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUser() {
        return new ResponseEntity<>(userService.getAllUser(), HttpStatus.OK);
    }

    @GetMapping("{userId}")
    public ResponseEntity<UserResponse> getUserByUserId(@PathVariable(name="userId") Long userId){
        UserResponse response = userService.getUserByUserId(userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> saveUser(@RequestBody UserRequest request) {
        Result result = userService.saveUser(request);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping(path = "{userId}")
    public ResponseEntity<?> updateUser(@PathVariable(name="userId") Long userId, @RequestBody UserRequest request) {
        if (request == null) {
            return new ResponseEntity<>("Your request is null!", HttpStatus.BAD_REQUEST);
        }
        else{
            Result response = userService.updateUser(userId , request);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

    }

    @DeleteMapping("{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable(name="userId") Long userId) {

        Result response = userService.deleteUser(userId);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

}
