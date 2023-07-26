/**
 * This class represents a Data Transfer Object (DTO) for vehicles.
 * It encapsulates the information related to a vehicle.
 */
package com.cvqs.mistakelistingservice.dto;

import java.util.List;

public class VehicleDto {

    private int id;
    private String chassisNumber;
    private int bandId;
    private String factoryName;
    private List<MistakeDto> mistakes;

    /**
     * Constructs a new VehicleDto with the provided values.
     *
     * @param id             the ID of the vehicle
     * @param chassisNumber  the chassis number of the vehicle
     * @param bandId         the ID of the associated band
     * @param factoryName    the name of the factory
     * @param mistakes       the list of mistakes associated with the vehicle
     */
    public VehicleDto(int id, String chassisNumber, int bandId, String factoryName, List<MistakeDto> mistakes) {
        this.id = id;
        this.chassisNumber = chassisNumber;
        this.bandId = bandId;
        this.factoryName = factoryName;
        this.mistakes = mistakes;
    }

    /**
     * Constructs a new VehicleDto with the provided values.
     *
     * @param chassisNumber  the chassis number of the vehicle
     * @param bandId         the ID of the associated band
     * @param factoryName    the name of the factory
     * @param mistakes       the list of mistakes associated with the vehicle
     */
    public VehicleDto(String chassisNumber, int bandId, String factoryName, List<MistakeDto> mistakes) {
        this.chassisNumber = chassisNumber;
        this.bandId = bandId;
        this.factoryName = factoryName;
        this.mistakes = mistakes;
    }

    /**
     * Constructs a new VehicleDto with the provided list of mistakes.
     *
     * @param mistakes the list of mistakes associated with the vehicle
     */
    public VehicleDto(List<MistakeDto> mistakes) {
        this.mistakes = mistakes;
    }

    /**
     * Constructs a new empty VehicleDto.
     */
    public VehicleDto() {
    }

    /**
     * Returns the ID of the vehicle.
     *
     * @return the ID of the vehicle
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the vehicle.
     *
     * @param id the ID of the vehicle to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the chassis number of the vehicle.
     *
     * @return the chassis number of the vehicle
     */
    public String getChassisNumber() {
        return chassisNumber;
    }

    /**
     * Sets the chassis number of the vehicle.
     *
     * @param chassisNumber the chassis number of the vehicle to set
     */
    public void setChassisNumber(String chassisNumber) {
        this.chassisNumber = chassisNumber;
    }

    /**
     * Returns the ID of the associated band.
     *
     * @return the ID of the associated band
     */
    public int getBandId() {
        return bandId;
    }

    /**
     * Sets the ID of the associated band.
     *
     * @param bandId the ID of the associated band to set
     */
    public void setBandId(int bandId) {
        this.bandId = bandId;
    }

    /**
     * Returns the name of the factory.
     *
     * @return the name of the factory
     */
    public String getFactoryName() {
        return factoryName;
    }

    /**
     * Sets the name of the factory.
     *
     * @param factoryName the name of the factory to set
     */
    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    /**
     * Returns the list of mistakes associated with the vehicle.
     *
     * @return the list of mistakes associated with the vehicle
     */
    public List<MistakeDto> getMistakes() {
        return mistakes;
    }

    /**
     * Sets the list of mistakes associated with the vehicle.
     *
     * @param mistakes the list of mistakes to set
     */
    public void setMistakes(List<MistakeDto> mistakes) {
        this.mistakes = mistakes;
    }
}
