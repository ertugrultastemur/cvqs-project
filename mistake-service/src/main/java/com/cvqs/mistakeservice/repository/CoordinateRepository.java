package com.cvqs.mistakeservice.repository;

import com.cvqs.mistakeservice.model.Coordinate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Coordinate entities.
 */
@Repository
public interface CoordinateRepository extends JpaRepository<Coordinate, Integer> {
}
