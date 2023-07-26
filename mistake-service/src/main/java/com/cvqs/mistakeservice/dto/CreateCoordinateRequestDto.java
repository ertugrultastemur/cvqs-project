package com.cvqs.mistakeservice.dto;

/**
 * DTO class representing a request to create a coordinate.
 */
public class CreateCoordinateRequestDto {

    private int x;
    private int y;
    private int mistakeId;

    /**
     * Constructs a new CreateCoordinateRequestDto with the specified properties.
     *
     * @param x          the x-coordinate value
     * @param y          the y-coordinate value
     * @param mistakeId  the ID of the associated mistake
     */
    public CreateCoordinateRequestDto(int x, int y, int mistakeId) {
        this.x = x;
        this.y = y;
        this.mistakeId = mistakeId;
    }

    /**
     * Constructs a new CreateCoordinateRequestDto with default values.
     */
    public CreateCoordinateRequestDto() {
    }

    /**
     * Retrieves the x-coordinate value.
     *
     * @return the x-coordinate value
     */
    public int getX() {
        return x;
    }

    /**
     * Sets the x-coordinate value.
     *
     * @param x the x-coordinate value
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Retrieves the y-coordinate value.
     *
     * @return the y-coordinate value
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the y-coordinate value.
     *
     * @param y the y-coordinate value
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Retrieves the ID of the associated mistake.
     *
     * @return the ID of the associated mistake
     */
    public int getMistakeId() {
        return mistakeId;
    }

    /**
     * Sets the ID of the associated mistake.
     *
     * @param mistakeId the ID of the associated mistake
     */
    public void setMistakeId(int mistakeId) {
        this.mistakeId = mistakeId;
    }
}
