package com.cvqs.usermanagementservice.repository;

import com.cvqs.usermanagementservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface userManagementRepository extends JpaRepository<User,Integer> {
}
