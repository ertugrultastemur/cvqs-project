package com.cvqs.mistakeservice.service;

import com.cvqs.mistakeservice.dto.CoordinateDto;
import com.cvqs.mistakeservice.dto.CreateCoordinateRequestDto;
import com.cvqs.mistakeservice.exception.CoordinateNotFoundException;
import com.cvqs.mistakeservice.exception.MistakeNotFoundException;
import com.cvqs.mistakeservice.exception.VehicleNotFoundException;
import com.cvqs.mistakeservice.mapper.ModelMapperService;
import com.cvqs.mistakeservice.model.Coordinate;
import com.cvqs.mistakeservice.repository.CoordinateRepository;
import com.cvqs.mistakeservice.repository.MistakeRepository;
import lombok.Builder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for Coordinate-related operations.
 */
@Service
public class CoordinateService {

    Logger logger = LoggerFactory.getLogger(CoordinateService.class);
    private final CoordinateRepository coordinateRepository;
    private final MistakeRepository mistakeRepository;
    private final ModelMapperService modelMapperService;

    /**
     * Constructs a new CoordinateService with the specified repositories and ModelMapperService.
     *
     * @param coordinateRepository the Coordinate repository
     * @param mistakeRepository    the Mistake repository
     * @param modelMapperService   the ModelMapperService
     */
    public CoordinateService(CoordinateRepository coordinateRepository, MistakeRepository mistakeRepository, ModelMapperService modelMapperService) {
        logger.info("CoordinateService: ctor entered");
        this.coordinateRepository = coordinateRepository;
        this.mistakeRepository = mistakeRepository;
        this.modelMapperService = modelMapperService;
    }

    /**
     * Retrieves all coordinates.
     *
     * @return the list of CoordinateDto objects
     */
    public List<CoordinateDto> getAll() {
        logger.info("CoordinateService: getAll method entered");
        return coordinateRepository.findAll().stream().map(CoordinateDto::convert).collect(Collectors.toList());
    }

    /**
     * Retrieves a Coordinate by its ID.
     *
     * @param id the ID of the Coordinate
     * @return the CoordinateDto object
     * @throws CoordinateNotFoundException if the Coordinate is not found
     */
    public CoordinateDto findById(int id) throws CoordinateNotFoundException {
        logger.info("CoordinateService: findById method entered");
        return coordinateRepository.findById(id).map(CoordinateDto::convert).orElseThrow(() -> new CoordinateNotFoundException("Coordinate could not be found by ID: " + id));
    }

    /**
     * Adds a new Coordinate.
     *
     * @param createCoordinateRequestDto the CreateCoordinateRequestDto object
     * @throws MistakeNotFoundException if the associated Mistake is not found
     */
    public void add(CreateCoordinateRequestDto createCoordinateRequestDto) throws MistakeNotFoundException {
        logger.info("CoordinateService: add method entered");
        Coordinate coordinate = this.modelMapperService.forRequest().map(createCoordinateRequestDto, Coordinate.class);
        coordinate.setCoordinateId(0);
        coordinate.mistake = mistakeRepository.findById(createCoordinateRequestDto.getMistakeId()).orElseThrow(() -> new MistakeNotFoundException("Mistake could not be found by ID: " + createCoordinateRequestDto.getMistakeId()));
        coordinateRepository.save(coordinate);
        logger.info("CoordinateService: coordinate saved");
    }

    /**
     * Deletes a Coordinate by its ID.
     *
     * @param id the ID of the Coordinate to delete
     * @throws CoordinateNotFoundException if the Coordinate is not found
     */
    public void delete(int id) throws CoordinateNotFoundException {
        logger.info("CoordinateService: delete method entered");
        coordinateRepository.findById(id).orElseThrow(() -> new CoordinateNotFoundException("Coordinate could not be found by ID: " + id));
    }
}
