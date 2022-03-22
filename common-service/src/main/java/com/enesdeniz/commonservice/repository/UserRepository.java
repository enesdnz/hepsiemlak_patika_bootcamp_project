package com.enesdeniz.commonservice.repository;

import com.enesdeniz.commonservice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User, Long> {


}
