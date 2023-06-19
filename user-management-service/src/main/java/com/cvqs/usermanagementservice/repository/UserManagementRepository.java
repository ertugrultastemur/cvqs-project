package com.cvqs.usermanagementservice.repository;

import com.cvqs.usermanagementservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserManagementRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUserName(String userName);
}
