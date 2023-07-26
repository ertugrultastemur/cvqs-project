/**
 * This class represents the controller for Mistake Listing API endpoints.
 * It handles HTTP requests related to mistake listings and provides various methods for retrieving and manipulating mistake data.
 */
package com.cvqs.mistakelistingservice.controller;

import com.cvqs.mistakelistingservice.dto.MistakeDto;
import com.cvqs.mistakelistingservice.dto.VehicleDto;
import com.cvqs.mistakelistingservice.service.MistakeListingService;
import jdk.jfr.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/mistake-listing")
public class MistakeListingController {

    private final MistakeListingService mistakeListingService;

    Logger logger = LoggerFactory.getLogger(MistakeListingController.class);

    /**
     * Constructs a new MistakeListingController with the provided MistakeListingService.
     *
     * @param mistakeListingService the MistakeListingService to be used by the controller
     */
    public MistakeListingController(MistakeListingService mistakeListingService) {
        logger.info("MistakeListingService: ctor entered.");
        this.mistakeListingService = mistakeListingService;
    }

    /**
     * Retrieves all mistake listings.
     *
     * @return a ResponseEntity containing a list of MistakeDto objects
     */
    @GetMapping("/getAll")
    public ResponseEntity<List<MistakeDto>> getALl() {
        logger.info("MistakeListingService: getAll method entered.");
        return ResponseEntity.ok(mistakeListingService.getAll());
    }

    /**
     * Retrieves the image of a mistake by its ID.
     *
     * @param id the ID of the mistake
     * @return a ResponseEntity containing the image data as a byte array
     * @throws IOException if an I/O error occurs
     */
    @GetMapping(value = "/getImageById/{id}")
    public ResponseEntity<byte[]> getImageById(@PathVariable int id) throws IOException {
        logger.info("MistakeListingService: getImageById method entered.");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        headers.setContentLength(mistakeListingService.getImageById(id).length);
        return new ResponseEntity<>(mistakeListingService.getImageById(id), headers, HttpStatus.OK);
    }

    /**
     * Writes the mistake image to a file.
     *
     * @param id the ID of the mistake
     * @return a ResponseEntity indicating the success of the write operation
     * @throws IOException if an I/O error occurs
     */
    @GetMapping(value = "/writeMistakeImage/{id}")
    public ResponseEntity<Void> writeMistakeImage(@PathVariable int id) throws IOException {
        logger.info("MistakeListingService: getAll method entered.");
        mistakeListingService.writeMistakeImage(id);
        logger.info("MistakeListingService: Image written.");
        return ResponseEntity.ok().build();
    }

    /**
     * Finds mistake listings with sorting based on a field.
     *
     * @param field the field to sort the mistake listings by
     * @return a ResponseEntity containing a list of MistakeDto objects sorted by the specified field
     */
    @GetMapping("/findMistakesWithSorting/{field}")
    public ResponseEntity<List<MistakeDto>> findMistakesWithSorting(@PathVariable String field) {
        logger.info("MistakeListingService: findMistakesWithSorting method entered.");
        return new ResponseEntity<>(mistakeListingService.findMistakesWithSorting(field).stream().toList(), HttpStatus.OK);
    }

    /**
     * Finds mistake listings with pagination.
     *
     * @param page  the page number
     * @param limit the maximum number of items per page
     * @return a ResponseEntity containing a Page of MistakeDto objects
     */
    @GetMapping("/findMistakesWithPagination")
    public ResponseEntity<Page<MistakeDto>> findMistakesWithPagination(int page, int limit) {
        logger.info("MistakeListingService: findMistakesWithPagination method entered.");
        return new ResponseEntity<>(mistakeListingService.findMistakesWithPagination(page, limit), HttpStatus.OK);
    }

    /**
     * Finds mistake listings with name filtering.
     *
     * @param field the field to filter the mistake listings by name
     * @return a ResponseEntity containing a list of MistakeDto objects filtered by the specified name
     */
    @GetMapping("/findMistakesWithNameFiltering/{field}")
    public ResponseEntity<List<MistakeDto>> findMistakesWithNameFiltering(@PathVariable String field) {
        logger.info("MistakeListingService: findMistakesWithNameFiltering method entered.");
        return new ResponseEntity<>(mistakeListingService.findMistakesWithNameFiltering(field).stream().toList(), HttpStatus.OK);
    }
}
