package com.hyd.backend.domain.repository;

import com.hyd.backend.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by methol on 6/24/16.
 */
@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long>,
  JpaSpecificationExecutor<User> {
}
