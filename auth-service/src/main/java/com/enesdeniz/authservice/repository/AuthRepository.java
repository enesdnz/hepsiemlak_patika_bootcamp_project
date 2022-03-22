package com.enesdeniz.authservice.repository;


import com.enesdeniz.authservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<User, Integer> {

	Optional<User> findByEmail(String email);

}
