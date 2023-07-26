package com.cvqs.mistakeservice.dto;

import com.cvqs.mistakeservice.exception.MistakeSQLException;
import com.cvqs.mistakeservice.model.Coordinate;
import com.cvqs.mistakeservice.model.Mistake;
import lombok.Builder;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Data Transfer Object (DTO) class representing a Mistake.
 */

public class MistakeDto {

    private Integer vehicleId;
    private MistakeIdDto id;
    private byte[] image;
    private List<CoordinateDto> coordinates;

    /**
     * Constructs a new MistakeDto object with the specified mistake ID, image blob, vehicle ID, and coordinates.
     *
     * @param mistakeId   the MistakeIdDto object representing the mistake ID and name
     * @param image       the image blob
     * @param vehicleId   the ID of the associated vehicle
     * @param coordinates the list of CoordinateDto objects representing the mistake's coordinates
     */
    public MistakeDto(MistakeIdDto mistakeId, Blob image, int vehicleId, List<CoordinateDto> coordinates) {
        this.id = mistakeId;
        this.image = convertBlobToBase641(image);
        this.vehicleId = vehicleId;
        this.coordinates = coordinates;
    }

    /**
     * Constructs a new MistakeDto object with the specified image blob.
     *
     * @param image the image blob
     */
    public MistakeDto(Blob image) {
        this.image = convertBlobToBase641(image);
    }

    /**
     * Default constructor for MistakeDto.
     */
    public MistakeDto() {
    }

    /**
     * Converts a Mistake object to a MistakeDto object.
     *
     * @param mistake the Mistake object to convert
     * @return the converted MistakeDto object
     */
    public static MistakeDto convert(Mistake mistake) {
        MistakeIdDto mistakeIdDto = new MistakeIdDto(mistake.getId(), mistake.getMistakeName());
        List<CoordinateDto> coordinates = mistake.getCoordinates()
                .stream()
                .map(CoordinateDto::convert)
                .collect(Collectors.toList());
        return new MistakeDto(
                mistakeIdDto,
                mistake.getImage(),
                mistake.vehicle.getId(),
                coordinates
        );
    }

    /**
     * Converts a Blob object to a byte array in base64 encoding.
     *
     * @param blob the Blob object to convert
     * @return the byte array in base64 encoding
     * @throws MistakeSQLException if an SQL exception occurs
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
     * Returns the MistakeIdDto object representing the mistake ID and name.
     *
     * @return the MistakeIdDto object
     */
    public MistakeIdDto getId() {
        return id;
    }

    /**
     * Sets the MistakeIdDto object representing the mistake ID and name.
     *
     * @param id the MistakeIdDto object to set
     */
    public void setId(MistakeIdDto id) {
        this.id = id;
    }

    /**
     * Returns the image byte array of the mistake.
     *
     * @return the image byte array
     */
    public byte[] getImage() {
        return image;
    }

    /**
     * Sets the image byte array of the mistake.
     *
     * @param image the image byte array to set
     */
    public void setImage(byte[] image) {
        this.image = image;
    }

    /**
     * Returns the ID of the associated vehicle.
     *
     * @return the vehicle ID
     */
    public int getVehicleId() {
        return vehicleId;
    }

    /**
     * Sets the ID of the associated vehicle.
     *
     * @param vehicleId the vehicle ID to set
     */
    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    /**
     * Returns the list of CoordinateDto objects representing the mistake's coordinates.
     *
     * @return the list of CoordinateDto objects
     */
    public List<CoordinateDto> getCoordinates() {
        return coordinates;
    }

    /**
     * Sets the list of CoordinateDto objects representing the mistake's coordinates.
     *
     * @param coordinates the list of CoordinateDto objects to set
     */
    public void setCoordinates(List<CoordinateDto> coordinates) {
        this.coordinates = coordinates;
    }

}
