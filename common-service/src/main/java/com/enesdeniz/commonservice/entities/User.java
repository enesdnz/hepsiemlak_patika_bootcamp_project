package com.enesdeniz.commonservice.entities;

import com.enesdeniz.commonservice.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "user")
public class User extends BaseEntity{

    @Enumerated(EnumType.STRING)
    private UserType userType;
    private String name;
    private String email;
    private String password;

}
