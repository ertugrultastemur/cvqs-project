/**
 * This class represents a Data Transfer Object (DTO) for updating an image.
 * It encapsulates the information related to the image update.
 */
package com.cvqs.mistakelistingservice.dto;

public class UpdateImageDto {

    int id;
    byte[] image;

    /**
     * Constructs a new UpdateImageDto with the provided image and ID.
     *
     * @param image the image data as a byte array
     * @param id    the ID of the entity to update
     */
    public UpdateImageDto(byte[] image, int id) {
        this.image = image;
        this.id = id;
    }

    /**
     * Constructs a new empty UpdateImageDto.
     */
    public UpdateImageDto() {
    }

    /**
     * Returns the ID of the entity to update.
     *
     * @return the ID of the entity to update
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the entity to update.
     *
     * @param id the ID of the entity to update
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the image data as a byte array.
     *
     * @return the image data as a byte array
     */
    public byte[] getImage() {
        return image;
    }

    /**
     * Sets the image data as a byte array.
     *
     * @param image the image data as a byte array
     */
    public void setImage(byte[] image) {
        this.image = image;
    }
}
