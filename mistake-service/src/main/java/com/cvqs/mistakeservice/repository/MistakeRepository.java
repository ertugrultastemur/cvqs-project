package com.cvqs.mistakeservice.repository;

import com.cvqs.mistakeservice.model.Mistake;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for Mistake entities.
 */
@Repository
public interface MistakeRepository extends JpaRepository<Mistake,Integer> {

}
