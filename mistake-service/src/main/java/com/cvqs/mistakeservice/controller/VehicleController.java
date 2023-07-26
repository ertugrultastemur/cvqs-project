package com.cvqs.mistakeservice.controller;

import com.cvqs.mistakeservice.dto.CreateMistakeRequestDto;
import com.cvqs.mistakeservice.dto.CreateVehicleRequestDto;
import com.cvqs.mistakeservice.dto.VehicleDto;
import com.cvqs.mistakeservice.model.Vehicle;
import com.cvqs.mistakeservice.service.VehicleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for handling Vehicle-related operations.
 */
@RestController
@RequestMapping("/v1/mistake/vehicle")
public class VehicleController {

    Logger logger = LoggerFactory.getLogger(VehicleController.class);
    private final VehicleService vehicleService;

    private Environment environment;

    /**
     * Constructs a new VehicleController with the specified VehicleService and Environment.
     *
     * @param vehicleService the VehicleService to be used
     * @param environment    the Environment for accessing environment-specific properties
     */
    public VehicleController(VehicleService vehicleService, Environment environment) {
        logger.info("VehicleController: ctor entered");
        this.vehicleService = vehicleService;
        this.environment = environment;
    }

    /**
     * Retrieves all VehicleDto entries.
     *
     * @return the ResponseEntity containing the list of VehicleDto entries
     */
    @GetMapping("/getAll")
    public ResponseEntity<List<VehicleDto>> getAll() {
        logger.info("VehicleController: getAll endpoint entered");
        return ResponseEntity.ok(this.vehicleService.getAllVehicles());
    }

    /**
     * Retrieves a VehicleDto entry by its ID.
     *
     * @param id the ID of the VehicleDto entry to retrieve
     * @return the ResponseEntity containing the retrieved VehicleDto entry
     */
    @GetMapping("/{id}")
    public ResponseEntity<VehicleDto> getById(@PathVariable int id) {
        logger.info("VehicleController: getById endpoint entered");
        return ResponseEntity.ok(this.vehicleService.getById(id));
    }

    /**
     * Adds a new VehicleDto entry.
     *
     * @param createVehicleRequestDto the CreateVehicleRequestDto containing the details of the new VehicleDto entry
     * @return the ResponseEntity indicating the success of the operation
     */
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> add(@RequestBody CreateVehicleRequestDto createVehicleRequestDto) {
        logger.info("Vehicle created on port number " + environment.getProperty("local.server.port"));
        this.vehicleService.add(createVehicleRequestDto);
        return ResponseEntity.ok().build();
    }

    /**
     * Updates a VehicleDto entry.
     *
     * @param vehicleDto the updated VehicleDto entry
     * @return the ResponseEntity indicating the success of the operation
     */
    @PutMapping("/update")
    public ResponseEntity<Void> update(@RequestBody VehicleDto vehicleDto) {
        logger.info("VehicleController: update endpoint entered");
        this.vehicleService.update(vehicleDto);
        logger.info("VehicleController: update endpoint success");
        return ResponseEntity.ok().build();
    }

    /**
     * Deletes a VehicleDto entry by its ID.
     *
     * @param id the ID of the VehicleDto entry to delete
     * @return the ResponseEntity indicating the success of the operation
     */
    @PutMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        logger.info("VehicleController: delete endpoint entered");
        this.vehicleService.delete(id);
        logger.info("VehicleController: delete endpoint success");
        return ResponseEntity.ok().build();
    }
}
