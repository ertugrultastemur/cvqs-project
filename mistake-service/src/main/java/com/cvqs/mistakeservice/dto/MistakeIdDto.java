package com.cvqs.mistakeservice.dto;

/**
 * Data Transfer Object (DTO) class representing a Mistake ID.
 */
public class MistakeIdDto {

    private int mistakeId;
    private String mistakeName;

    /**
     * Constructs a new MistakeIdDto object with the specified mistake ID and mistake name.
     *
     * @param mistakeId   the mistake ID
     * @param mistakeName the mistake name
     */
    public MistakeIdDto(int mistakeId, String mistakeName) {
        this.mistakeId = mistakeId;
        this.mistakeName = mistakeName;
    }

    /**
     * Default constructor for MistakeIdDto.
     */
    public MistakeIdDto() {
    }

    /**
     * Converts the provided mistake ID and mistake name to a MistakeIdDto object.
     *
     * @param mistakeId   the mistake ID
     * @param mistakeName the mistake name
     * @return the MistakeIdDto object
     */
    public static MistakeIdDto convert(int mistakeId, String mistakeName) {
        return new MistakeIdDto(mistakeId, mistakeName);
    }

    /**
     * Returns the mistake ID.
     *
     * @return the mistake ID
     */
    public int getMistakeId() {
        return mistakeId;
    }

    /**
     * Sets the mistake ID.
     *
     * @param mistakeId the mistake ID to set
     */
    public void setMistakeId(int mistakeId) {
        this.mistakeId = mistakeId;
    }

    /**
     * Returns the mistake name.
     *
     * @return the mistake name
     */
    public String getMistakeName() {
        return mistakeName;
    }

    /**
     * Sets the mistake name.
     *
     * @param mistakeName the mistake name to set
     */
    public void setMistakeName(String mistakeName) {
        this.mistakeName = mistakeName;
    }
}
