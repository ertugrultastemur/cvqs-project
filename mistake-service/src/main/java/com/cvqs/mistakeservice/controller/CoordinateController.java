package com.cvqs.mistakeservice.controller;

import com.cvqs.mistakeservice.dto.CoordinateDto;
import com.cvqs.mistakeservice.dto.CreateCoordinateRequestDto;
import com.cvqs.mistakeservice.service.CoordinateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for handling Coordinate-related operations.
 */
@RestController
@RequestMapping("/v1/mistake/coordinate")
public class CoordinateController {

    Logger logger = LoggerFactory.getLogger(CoordinateController.class);
    private final CoordinateService coordinateService;

    /**
     * Constructs a new CoordinateController with the specified CoordinateService.
     *
     * @param coordinateService the CoordinateService to be used
     */
    public CoordinateController(CoordinateService coordinateService) {
        logger.info("CoordinateController: ctor entered");
        this.coordinateService = coordinateService;
    }

    /**
     * Retrieves all CoordinateDto entries.
     *
     * @return the ResponseEntity containing the list of CoordinateDto entries
     */
    @GetMapping("/getAll")
    public ResponseEntity<List<CoordinateDto>> getAll() {
        logger.info("CoordinateController: getAll endpoint entered");
        return ResponseEntity.ok(coordinateService.getAll());
    }

    /**
     * Retrieves a CoordinateDto entry by its ID.
     *
     * @param id the ID of the CoordinateDto entry to retrieve
     * @return the ResponseEntity containing the retrieved CoordinateDto entry
     */
    @GetMapping("/{id}")
    public ResponseEntity<CoordinateDto> getById(@PathVariable int id) {
        logger.info("CoordinateController: getById endpoint entered" + id);
        return ResponseEntity.ok(coordinateService.findById(id));
    }

    /**
     * Adds a new CoordinateDto entry.
     *
     * @param createCoordinateRequestDto the CreateCoordinateRequestDto containing the details of the new CoordinateDto entry
     * @return the ResponseEntity indicating the success of the operation
     */
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> add(@RequestBody CreateCoordinateRequestDto createCoordinateRequestDto) {
        logger.info("CoordinateController: add endpoint entered");
        coordinateService.add(createCoordinateRequestDto);
        logger.info("CoordinateController: add endpoint success.");
        return ResponseEntity.ok().build();
    }

    /**
     * Deletes a CoordinateDto entry by its ID.
     *
     * @param id the ID of the CoordinateDto entry to delete
     * @return the ResponseEntity indicating the success of the operation
     */
    @PutMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        logger.info("CoordinateController: delete endpoint entered");
        coordinateService.delete(id);
        logger.info("CoordinateController: delete endpoint success");
        return ResponseEntity.ok().build();
    }
}
