package com.cvqs.mistakeservice.repository;

import com.cvqs.mistakeservice.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Vehicle entities.
 */
@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
}
