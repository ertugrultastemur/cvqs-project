package com.cvqs.mistakeservice.dto;

/**
 * DTO class used for creating a new mistake.
 */
public class CreateMistakeRequestDto {

    private String mistakeName;
    private int vehicleId;

    /**
     * Constructor for CreateMistakeRequestDto class with parameters.
     *
     * @param vehicleId   the vehicle ID
     * @param mistakeName the mistake name
     */
    public CreateMistakeRequestDto(int vehicleId, String mistakeName) {
        this.vehicleId = vehicleId;
        this.mistakeName = mistakeName;
    }

    /**
     * Default constructor for CreateMistakeRequestDto class.
     */
    public CreateMistakeRequestDto() {
    }

    /**
     * Gets the vehicle ID.
     *
     * @return the vehicle ID
     */
    public int getVehicleId() {
        return vehicleId;
    }

    /**
     * Sets the vehicle ID.
     *
     * @param vehicleId the vehicle ID
     */
    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    /**
     * Gets the mistake name.
     *
     * @return the mistake name
     */
    public String getMistakeName() {
        return mistakeName;
    }

    /**
     * Sets the mistake name.
     *
     * @param mistakeName the mistake name
     */
    public void setMistakeName(String mistakeName) {
        this.mistakeName = mistakeName;
    }
}
