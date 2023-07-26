package com.cvqs.mistakeservice.controller;

import com.cvqs.mistakeservice.dto.*;
import com.cvqs.mistakeservice.exception.ImageIOException;
import com.cvqs.mistakeservice.service.MistakeService;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Controller class for handling Mistake-related operations.
 */
@RestController
@RequestMapping("/v1/mistake/mistake")
public class MistakeController {

    Logger logger = LoggerFactory.getLogger(MistakeController.class);
    private final MistakeService mistakeService;

    /**
     * Constructs a new MistakeController with the specified MistakeService.
     *
     * @param mistakeService the MistakeService to be used
     */
    public MistakeController(MistakeService mistakeService) {
        logger.info("MistakeController: ctor entered.");
        this.mistakeService = mistakeService;
    }

    /**
     * Retrieves all MistakeDto entries.
     *
     * @return the ResponseEntity containing the list of MistakeDto entries
     */
    @GetMapping("/getAll")
    public ResponseEntity<List<MistakeDto>> getAll() {
        logger.info("MistakeController: getAll endpoint entered.");
        return ResponseEntity.ok(mistakeService.getAllMistakes());
    }

    /**
     * Retrieves a MistakeDto entry by its ID.
     *
     * @param id the ID of the MistakeDto entry to retrieve
     * @return the ResponseEntity containing the retrieved MistakeDto entry
     */
    @GetMapping("/{id}")
    public ResponseEntity<MistakeDto> getById(@PathVariable int id) {
        logger.info("MistakeController: getById endpoint entered.");
        return ResponseEntity.ok(mistakeService.findById(id));
    }

    /**
     * Adds a new MistakeDto entry.
     *
     * @param multipartFile the MultipartFile containing the image file
     * @param mistakeDto    the CreateMistakeRequestDto containing the details of the new MistakeDto entry
     * @return the ResponseEntity indicating the success of the operation
     * @throws Exception if an exception occurs during the operation
     */
    @PostMapping(path = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> add(@RequestParam("image") MultipartFile multipartFile, @RequestPart("mistake") @ModelAttribute CreateMistakeRequestDto mistakeDto) throws Exception {
        logger.info("MistakeController: add endpoint entered.");
        mistakeService.addMistake(multipartFile, mistakeDto);
        return ResponseEntity.ok().build();
    }

    /**
     * Updates the image of a MistakeDto entry.
     *
     * @param imageDto the UpdateImageDto containing the updated image information
     * @return the ResponseEntity indicating the success of the operation
     * @throws SQLException if a SQL exception occurs during the operation
     */
    @PutMapping("/update")
    public ResponseEntity<Void> update(@RequestBody UpdateImageDto imageDto) throws SQLException {
        logger.info("MistakeController: update endpoint entered.");
        this.mistakeService.update(imageDto);
        logger.info("MistakeController: update endpoint success.");
        return ResponseEntity.ok().build();
    }

    /**
     * Deletes a MistakeDto entry by its ID.
     *
     * @param id the ID of the MistakeDto entry to delete
     * @return the ResponseEntity indicating the success of the operation
     */
    @PutMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable @NotBlank int id) {
        logger.info("MistakeController: delete endpoint entered.");
        mistakeService.delete(id);
        logger.info("MistakeController: delete endpoint success.");
        return ResponseEntity.ok().build();
    }
}
