/**
 * This class represents a Data Transfer Object (DTO) for Mistake entities.
 * It encapsulates the information related to a mistake.
 */
package com.cvqs.mistakelistingservice.dto;

import com.cvqs.mistakelistingservice.exception.MistakeSQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

public class MistakeDto {

    Logger logger = LoggerFactory.getLogger(MistakeDto.class);
    private int vehicleId;
    private MistakeIdDto id;
    private byte[] image;
    private List<CoordinateDto> coordinates;

    /**
     * Constructs a new MistakeDto with the provided values.
     *
     * @param mistakeId   the MistakeIdDto representing the mistake ID
     * @param image       the image data as a Blob
     * @param vehicleId   the ID of the associated vehicle
     * @param coordinates the list of coordinates associated with the mistake
     */
    public MistakeDto(MistakeIdDto mistakeId, Blob image, int vehicleId, List<CoordinateDto> coordinates) {
        logger.info("MistakeDto: ctor entered.");
        this.id = mistakeId;
        this.image = convertBlobToBase641(image);
        this.vehicleId = vehicleId;
        this.coordinates = coordinates;
    }

    /**
     * Constructs a new MistakeDto with the provided image data.
     *
     * @param image the image data as a Blob
     */
    public MistakeDto(Blob image) {
        logger.info("MistakeDto: ctor entered.");
        this.image = convertBlobToBase641(image);
    }

    /**
     * Constructs a new empty MistakeDto.
     */
    public MistakeDto() {
        logger.info("MistakeDto: ctor entered.");
    }

    /**
     * Converts a Blob object to a base64-encoded byte array.
     *
     * @param blob the Blob object to convert
     * @return the base64-encoded byte array representing the Blob data
     * @throws MistakeSQLException if a SQL or I/O error occurs
     */
    private static byte[] convertBlobToBase641(Blob blob) {
        try (InputStream inputStream = blob.getBinaryStream()) {
            int blobLength = (int) blob.length();
            byte[] bytes = new byte[blobLength];
            inputStream.read(bytes, 0, blobLength);
            return bytes;
        } catch (SQLException | IOException e) {
            throw new MistakeSQLException(e.getMessage());
        }
    }

    /**
     * Returns the ID of the associated vehicle.
     *
     * @return the ID of the associated vehicle
     */
    public int getVehicleId() {
        logger.info("MistakeDto: getVehicleId method getting: " + vehicleId);
        return vehicleId;
    }

    /**
     * Sets the ID of the associated vehicle.
     *
     * @param vehicleId the ID of the associated vehicle to set
     */
    public void setVehicleId(int vehicleId) {
        logger.info("MistakeDto: setVehicleId method setting: " + vehicleId);
        this.vehicleId = vehicleId;
    }

    /**
     * Returns the MistakeIdDto representing the mistake ID.
     *
     * @return the MistakeIdDto representing the mistake ID
     */
    public MistakeIdDto getId() {
        logger.info("MistakeDto: getId method getting: " + id);
        return id;
    }

    /**
     * Sets the MistakeIdDto representing the mistake ID.
     *
     * @param id the MistakeIdDto representing the mistake ID to set
     */
    public void setId(MistakeIdDto id) {
        logger.info("MistakeDto: setId method setting: " + id);
        this.id = id;
    }

    /**
     * Returns the image data as a byte array.
     *
     * @return the image data as a byte array
     */
    public byte[] getImage() {
        logger.info("MistakeDto: getImage method getting: image");
        return image;
    }

    /**
     * Sets the image data as a byte array.
     *
     * @param image the image data as a byte array to set
     */
    public void setImage(byte[] image) {
        logger.info("MistakeDto: setImage method setting: image");
        this.image = image;
    }

    /**
     * Returns the list of coordinates associated with the mistake.
     *
     * @return the list of coordinates associated with the mistake
     */
    public List<CoordinateDto> getCoordinates() {
        logger.info("MistakeDto: getCoordinates method getting: " + coordinates);
        return coordinates;
    }

    /**
     * Sets the list of coordinates associated with the mistake.
     *
     * @param coordinates the list of coordinates to set
     */
    public void setCoordinates(List<CoordinateDto> coordinates) {
        logger.info("MistakeDto: setCoordinates method setting: " + coordinates);
        this.coordinates = coordinates;
    }
}
