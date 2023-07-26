/**
 * This class represents a Data Transfer Object (DTO) for coordinates.
 * It encapsulates the information related to a coordinate.
 */
package com.cvqs.mistakelistingservice.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CoordinateDto {

    Logger logger = LoggerFactory.getLogger(CoordinateDto.class);

    private int id;
    private int x;
    private int y;
    private int mistakeId;

    /**
     * Constructs a new CoordinateDto with the provided values.
     *
     * @param id        the ID of the coordinate
     * @param x         the x-coordinate value
     * @param y         the y-coordinate value
     * @param mistakeId the ID of the associated mistake
     */
    public CoordinateDto(int id, int x, int y, int mistakeId) {
        logger.info("CoordinateDto: ctor entered.");
        this.id = id;
        this.x = x;
        this.y = y;
        this.mistakeId = mistakeId;
    }

    /**
     * Constructs a new empty CoordinateDto.
     */
    public CoordinateDto() {
        logger.info("CoordinateDto: ctor entered.");
    }

    /**
     * Returns the ID of the coordinate.
     *
     * @return the ID of the coordinate
     */
    public int getId() {
        logger.info("CoordinateDto: getId method is getting: " + id);
        return id;
    }

    /**
     * Sets the ID of the coordinate.
     *
     * @param id the ID to set for the coordinate
     */
    public void setId(int id) {
        logger.info("CoordinateDto: setId method is setting: " + id);
        this.id = id;
    }

    /**
     * Returns the x-coordinate value.
     *
     * @return the x-coordinate value
     */
    public int getX() {
        logger.info("CoordinateDto: getX method is getting: " + x);
        return x;
    }

    /**
     * Sets the x-coordinate value.
     *
     * @param x the x-coordinate value to set
     */
    public void setX(int x) {
        logger.info("CoordinateDto: setX method is setting: " + x);
        this.x = x;
    }

    /**
     * Returns the y-coordinate value.
     *
     * @return the y-coordinate value
     */
    public int getY() {
        logger.info("CoordinateDto: getY method is getting: " + y);
        return y;
    }

    /**
     * Sets the y-coordinate value.
     *
     * @param y the y-coordinate value to set
     */
    public void setY(int y) {
        logger.info("CoordinateDto: setY method is setting: " + y);
        this.y = y;
    }

    /**
     * Returns the ID of the associated mistake.
     *
     * @return the ID of the associated mistake
     */
    public int getMistakeId() {
        logger.info("CoordinateDto: getMistakeId method is getting: " + mistakeId);
        return mistakeId;
    }

    /**
     * Sets the ID of the associated mistake.
     *
     * @param mistakeId the ID of the associated mistake to set
     */
    public void setMistakeId(int mistakeId) {
        logger.info("CoordinateDto: setMistakeId method is setting: " + mistakeId);
        this.mistakeId = mistakeId;
    }
}
