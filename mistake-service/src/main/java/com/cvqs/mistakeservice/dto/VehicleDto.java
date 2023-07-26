package com.cvqs.mistakeservice.dto;

import com.cvqs.mistakeservice.model.Mistake;
import com.cvqs.mistakeservice.model.Vehicle;
import lombok.Builder;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Data Transfer Object (DTO) class representing a Vehicle.
 */
@Builder
public class VehicleDto {

    private int id;
    private String chassisNumber;
    private int bandId;
    private String factoryName;
    private List<MistakeDto> mistakes;

    /**
     * Constructs a new VehicleDto object with the specified properties.
     *
     * @param id            the vehicle ID
     * @param chassisNumber the chassis number
     * @param bandId        the band ID
     * @param factoryName   the factory name
     * @param mistakes      the list of MistakeDto objects
     */
    public VehicleDto(int id, String chassisNumber, int bandId, String factoryName, List<MistakeDto> mistakes) {
        this.id = id;
        this.chassisNumber = chassisNumber;
        this.bandId = bandId;
        this.factoryName = factoryName;
        this.mistakes = mistakes;
    }

    /**
     * Default constructor for VehicleDto.
     */
    public VehicleDto() {
    }

    /**
     * Converts a Vehicle object to a VehicleDto object.
     *
     * @param vehicle the Vehicle object to convert
     * @return the converted VehicleDto object
     */
    public static VehicleDto convert(Vehicle vehicle) {
        List<MistakeDto> mistakes = vehicle.getMistakes()
                .stream()
                .map(MistakeDto::convert)
                .collect(Collectors.toList());
        return new VehicleDto(
                vehicle.getId(),
                vehicle.getChassisNumber(),
                vehicle.getBandId(),
                vehicle.getFactoryName(),
                mistakes
        );
    }

    /**
     * Returns the vehicle ID.
     *
     * @return the vehicle ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the vehicle ID.
     *
     * @param id the vehicle ID to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the chassis number.
     *
     * @return the chassis number
     */
    public String getChassisNumber() {
        return chassisNumber;
    }

    /**
     * Sets the chassis number.
     *
     * @param chassisNumber the chassis number to set
     */
    public void setChassisNumber(String chassisNumber) {
        this.chassisNumber = chassisNumber;
    }

    /**
     * Returns the band ID.
     *
     * @return the band ID
     */
    public int getBandId() {
        return bandId;
    }

    /**
     * Sets the band ID.
     *
     * @param bandId the band ID to set
     */
    public void setBandId(int bandId) {
        this.bandId = bandId;
    }

    /**
     * Returns the factory name.
     *
     * @return the factory name
     */
    public String getFactoryName() {
        return factoryName;
    }

    /**
     * Sets the factory name.
     *
     * @param factoryName the factory name to set
     */
    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    /**
     * Returns the list of MistakeDto objects.
     *
     * @return the list of MistakeDto objects
     */
    public List<MistakeDto> getMistakes() {
        return mistakes;
    }

    /**
     * Sets the list of MistakeDto objects.
     *
     * @param mistakes the list of MistakeDto objects to set
     */
    public void setMistakes(List<MistakeDto> mistakes) {
        this.mistakes = mistakes;
    }
}
