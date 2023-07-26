package com.cvqs.mistakeservice.service;

import com.cvqs.mistakeservice.dto.CreateVehicleRequestDto;
import com.cvqs.mistakeservice.dto.MistakeDto;
import com.cvqs.mistakeservice.dto.VehicleDto;
import com.cvqs.mistakeservice.exception.VehicleNotFoundException;
import com.cvqs.mistakeservice.mapper.ModelMapperService;
import com.cvqs.mistakeservice.model.Vehicle;
import com.cvqs.mistakeservice.repository.VehicleRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for Vehicle-related operations.
 */
@Service
@Transactional
public class VehicleService {

    Logger logger = LoggerFactory.getLogger(VehicleService.class);
    private final VehicleRepository repository;
    private final ModelMapperService modelMapperService;

    /**
     * Constructs a new VehicleService with the specified repository and ModelMapperService.
     *
     * @param vehicleRepository  the Vehicle repository
     * @param modelMapperService the ModelMapperService
     */
    public VehicleService(VehicleRepository vehicleRepository, ModelMapperService modelMapperService) {
        logger.info("VehicleService: ctor entered");
        this.repository = vehicleRepository;
        this.modelMapperService = modelMapperService;
    }

    /**
     * Retrieves all Vehicles.
     *
     * @return the list of VehicleDto objects
     */
    public List<VehicleDto> getAllVehicles() {
        logger.info("VehicleService: getAllVehicles method entered");
        return repository.findAll().stream().map(VehicleDto::convert).collect(Collectors.toList());
    }

    /**
     * Retrieves a Vehicle by its ID.
     *
     * @param id the ID of the Vehicle
     * @return the VehicleDto object
     * @throws VehicleNotFoundException if the Vehicle is not found
     */
    public VehicleDto getById(int id) throws VehicleNotFoundException {
        logger.info("VehicleService: getById method entered");
        return repository.findById(id).map(VehicleDto::convert).orElseThrow(() -> new VehicleNotFoundException("Vehicle could not be found by ID: " + id));
    }

    /**
     * Adds a new Vehicle.
     *
     * @param createVehicleRequestDto the CreateVehicleRequestDto object
     */
    public void add(CreateVehicleRequestDto createVehicleRequestDto) {
        logger.info("VehicleService: add method entered");
        Vehicle vehicle = this.modelMapperService.forRequest().map(createVehicleRequestDto, Vehicle.class);
        vehicle.setId(0);
        repository.save(vehicle);
        logger.info("VehicleService: vehicle added successfully");
    }

    /**
     * Updates a Vehicle.
     *
     * @param vehicleDto the VehicleDto object containing the updated information
     */
    public void update(VehicleDto vehicleDto) {
        logger.info("VehicleService: update method entered");
        Vehicle vehicle = this.modelMapperService.forRequest().map(vehicleDto, Vehicle.class);
        repository.findById(vehicleDto.getId()).map(vehicleEntity -> vehicleDto).orElseThrow(() -> new VehicleNotFoundException("Vehicle could not be found by ID: " + vehicleDto.getId()));
    }

    /**
     * Deletes a Vehicle by its ID.
     *
     * @param id the ID of the Vehicle to delete
     * @throws VehicleNotFoundException if the Vehicle is not found
     */
    public void delete(int id) throws VehicleNotFoundException {
        logger.info("VehicleService: delete method entered");
        Vehicle vehicle = repository.findById(id).orElseThrow(() -> new VehicleNotFoundException("Vehicle could not be found by ID: " + id));
        vehicle.setDeleted(true);
        repository.save(vehicle);
        logger.info("VehicleService: vehicle deleted successfully");
    }
}
