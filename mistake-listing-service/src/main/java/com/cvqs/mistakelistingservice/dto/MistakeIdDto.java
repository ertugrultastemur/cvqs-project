/**
 * This class represents a Data Transfer Object (DTO) for Mistake IDs.
 * It encapsulates the information related to a mistake ID.
 */
package com.cvqs.mistakelistingservice.dto;

public class MistakeIdDto {

    private int mistakeId;
    private String mistakeName;

    /**
     * Constructs a new MistakeIdDto with the provided values.
     *
     * @param mistakeId   the mistake ID
     * @param mistakeName the name of the mistake
     */
    public MistakeIdDto(int mistakeId, String mistakeName) {
        this.mistakeId = mistakeId;
        this.mistakeName = mistakeName;
    }

    /**
     * Constructs a new empty MistakeIdDto.
     */
    public MistakeIdDto() {

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
     * Returns the name of the mistake.
     *
     * @return the name of the mistake
     */
    public String getMistakeName() {
        return mistakeName;
    }

    /**
     * Sets the name of the mistake.
     *
     * @param mistakeName the name of the mistake to set
     */
    public void setMistakeName(String mistakeName) {
        this.mistakeName = mistakeName;
    }
}
