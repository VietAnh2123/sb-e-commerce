package com.anhnhvcoder.ecommerce.repository;

import com.anhnhvcoder.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{

    Optional<User> findByEmail(String email);

    User findByPhone(String phone);
}
