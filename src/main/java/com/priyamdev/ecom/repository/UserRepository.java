package com.priyamdev.ecom.repository;

import com.priyamdev.ecom.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

//    List<User> findByRoles(String role);

    Boolean existsByEmail(String email);
}
