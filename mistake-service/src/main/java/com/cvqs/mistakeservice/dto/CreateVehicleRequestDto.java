package com.cvqs.mistakeservice.dto;

/**
 * Data Transfer Object (DTO) class used for creating a new vehicle.
 */
public class CreateVehicleRequestDto {

    private String chassisNumber;
    private int bandId;
    private String factoryName;

    /**
     * Constructs a new CreateVehicleRequestDto object with the specified chassis number, band ID, and factory name.
     *
     * @param chassisNumber the chassis number of the vehicle
     * @param bandId        the ID of the band associated with the vehicle
     * @param factoryName   the name of the factory where the vehicle is produced
     */
    public CreateVehicleRequestDto(String chassisNumber, int bandId, String factoryName) {
        this.chassisNumber = chassisNumber;
        this.bandId = bandId;
        this.factoryName = factoryName;
    }

    /**
     * Default constructor for CreateVehicleRequestDto.
     */
    public CreateVehicleRequestDto() {
    }

    /**
     * Returns the ID of the band associated with the vehicle.
     *
     * @return the band ID
     */
    public int getBandId() {
        return bandId;
    }

    /**
     * Sets the ID of the band associated with the vehicle.
     *
     * @param bandId the band ID to set
     */
    public void setBandId(int bandId) {
        this.bandId = bandId;
    }

    /**
     * Returns the chassis number of the vehicle.
     *
     * @return the chassis number
     */
    public String getChassisNumber() {
        return chassisNumber;
    }

    /**
     * Sets the chassis number of the vehicle.
     *
     * @param chassisNumber the chassis number to set
     */
    public void setChassisNumber(String chassisNumber) {
        this.chassisNumber = chassisNumber;
    }

    /**
     * Returns the name of the factory where the vehicle is produced.
     *
     * @return the factory name
     */
    public String getFactoryName() {
        return factoryName;
    }

    /**
     * Sets the name of the factory where the vehicle is produced.
     *
     * @param factoryName the factory name to set
     */
    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }
}
