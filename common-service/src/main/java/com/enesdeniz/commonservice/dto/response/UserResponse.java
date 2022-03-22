package com.enesdeniz.commonservice.dto.response;

import com.enesdeniz.commonservice.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private Long userId;
    private UserType userType;
    private String name;
    private String email;

}
