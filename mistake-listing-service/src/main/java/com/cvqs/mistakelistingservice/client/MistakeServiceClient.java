/**
 * This interface represents a client for the Mistake Listing Service.
 * It provides methods to retrieve mistake information from the service.
 */
package com.cvqs.mistakelistingservice.client;

import com.cvqs.mistakelistingservice.dto.MistakeDto;
import com.cvqs.mistakelistingservice.dto.UpdateImageDto;
import com.cvqs.mistakelistingservice.dto.VehicleDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "mistake-service", path = "/v1/mistake")
public interface MistakeServiceClient {

    /**
     * Retrieves all mistakes.
     *
     * @return a ResponseEntity containing a list of MistakeDto objects
     */
    @GetMapping("/mistake/getAll")
    ResponseEntity<List<MistakeDto>> getAll();

    /**
     * Retrieves a mistake by its ID.
     *
     * @param id the ID of the mistake
     * @return a ResponseEntity containing the MistakeDto object
     */
    @GetMapping("/mistake/{id}")
    ResponseEntity<MistakeDto> getById(@PathVariable int id);

    /**
     * Updates an image for a mistake.
     *
     * @param imageDto the UpdateImageDto object containing the updated image information
     * @return a ResponseEntity indicating the success of the update
     */
    @PutMapping("/mistake/update")
    ResponseEntity<Void> update(@RequestBody UpdateImageDto imageDto);
}
