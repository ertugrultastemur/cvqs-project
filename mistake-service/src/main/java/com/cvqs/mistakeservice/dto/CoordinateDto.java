package com.cvqs.mistakeservice.dto;

import com.cvqs.mistakeservice.model.Coordinate;
import lombok.Builder;

/**
 * DTO class representing a Coordinate.
 */
@Builder
public class CoordinateDto {

    private int id;
    private int x;
    private int y;
    private int mistakeId;

    /**
     * Constructs a new CoordinateDto with the specified properties.
     *
     * @param id         the ID of the coordinate
     * @param x          the x-coordinate value
     * @param y          the y-coordinate value
     * @param mistakeId  the ID of the associated mistake
     */
    public CoordinateDto(int id, int x, int y, int mistakeId) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.mistakeId = mistakeId;
    }

    /**
     * Converts a Coordinate entity to a CoordinateDto.
     *
     * @param coordinate the Coordinate entity to convert
     * @return the converted CoordinateDto
     */
    public static CoordinateDto convert(Coordinate coordinate) {
        return new CoordinateDto(
                coordinate.getCoordinateId(),
                coordinate.getX(),
                coordinate.getY(),
                coordinate.getMistake().getId()
        );
    }

    /**
     * Retrieves the ID of the coordinate.
     *
     * @return the ID of the coordinate
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the coordinate.
     *
     * @param id the ID of the coordinate
     */
    public void setId(int id) {
        this.id = id;
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
