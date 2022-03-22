package com.enesdeniz.updateAdvertStatusservice.entities;

import com.enesdeniz.updateAdvertStatusservice.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User{

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long id;
    @Enumerated(EnumType.STRING)
    private UserType userType;
    private String name;
    private String email;
    private String password;

}
