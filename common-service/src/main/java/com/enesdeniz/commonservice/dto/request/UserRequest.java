package com.enesdeniz.commonservice.dto.request;

import com.enesdeniz.commonservice.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    private UserType userType;
    private String name;
    private String email;

}
