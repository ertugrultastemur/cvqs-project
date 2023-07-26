package com.cvqs.mistakeservice.dto;

/**
 * Data Transfer Object (DTO) class representing an update image request.
 */
public class UpdateImageDto {

    private int id;
    private byte[] image;

    /**
     * Constructs a new UpdateImageDto object with the specified image and ID.
     *
     * @param image the image byte array
     * @param id    the ID
     */
    public UpdateImageDto(byte[] image, int id) {
        this.image = image;
        this.id = id;
    }

    /**
     * Default constructor for UpdateImageDto.
     */
    public UpdateImageDto() {
    }

    /**
     * Returns the ID.
     *
     * @return the ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID.
     *
     * @param id the ID to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the image byte array.
     *
     * @return the image byte array
     */
    public byte[] getImage() {
        return image;
    }

    /**
     * Sets the image byte array.
     *
     * @param image the image byte array to set
     */
    public void setImage(byte[] image) {
        this.image = image;
    }
}
