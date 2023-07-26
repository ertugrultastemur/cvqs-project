package com.cvqs.mistakeservice.model;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity class representing a Mistake.
 */
@Table(name = "mistakes")
@Entity
@Builder
@AllArgsConstructor
@Transactional
@SQLDelete(sql = "UPDATE mistakes SET is_deleted = true WHERE id=id")
@Where(clause = "is_deleted=false")
public class Mistake {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "mistake_name")
    private String mistakeName;

    @Lob
    @Column(name = "image")
    private Blob image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id")
    public Vehicle vehicle;

    @OneToMany(mappedBy = "mistake", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    List<Coordinate> coordinates = new ArrayList<>();

    @Column(name = "is_deleted")
    private boolean isDeleted = false;

    /**
     * Constructs a new Mistake object with the specified ID, mistake name, list of coordinates, image, and deletion status.
     *
     * @param id           the ID
     * @param mistakeName  the mistake name
     * @param coordinates  the list of coordinates
     * @param image        the image blob
     * @param isDeleted    the deletion status
     */
    public Mistake(int id, String mistakeName, List<Coordinate> coordinates, Blob image, boolean isDeleted) {
        this.id = id;
        this.mistakeName = mistakeName;
        this.coordinates = coordinates;
        this.image = image;
        this.isDeleted = isDeleted;
    }

    /**
     * Default constructor for Mistake.
     */
    public Mistake() {
    }

    /**
     * Returns the ID of the Mistake.
     *
     * @return the ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the Mistake.
     *
     * @param id the ID to set
     */
    public void setId(int id) {
        this.id = id;
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

    /**
     * Returns the list of coordinates associated with the Mistake.
     *
     * @return the list of coordinates
     */
    public List<Coordinate> getCoordinates() {
        return coordinates;
    }

    /**
     * Sets the list of coordinates associated with the Mistake.
     *
     * @param coordinates the list of coordinates to set
     */
    public void setCoordinates(List<Coordinate> coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * Returns the deletion status of the Mistake.
     *
     * @return the deletion status
     */
    public boolean isDeleted() {
        return isDeleted;
    }

    /**
     * Sets the deletion status of the Mistake.
     *
     * @param deleted the deletion status to set
     */
    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    /**
     * Returns the image blob associated with the Mistake.
     *
     * @return the image blob
     */
    public Blob getImage() {
        return image;
    }

    /**
     * Sets the image blob associated with the Mistake.
     *
     * @param image the image blob to set
     */
    public void setImage(Blob image) {
        this.image = image;
    }
}
