package com.cvqs.mistakeservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import org.hibernate.Interceptor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.internal.CoordinatingEntityNameResolver;

import java.io.Serializable;

/**
 * Entity class representing a Coordinate.
 */
@Entity
@Builder
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "coordinates")
@SQLDelete(sql = "UPDATE coordinates SET is_deleted = true WHERE id=id")
@Where(clause = "is_deleted=false")
public class Coordinate implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer coordinateId;

    @Column(name = "x_coordinate")
    private Integer x;

    @Column(name = "y_coordinate")
    private Integer y;

    @Column(name = "is_deleted")
    private boolean isDeleted = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mistake_id")
    public Mistake mistake;

    /**
     * Constructs a new Coordinate object with the specified coordinate ID, x-coordinate, y-coordinate, and deletion status.
     *
     * @param coordinateId the coordinate ID
     * @param x            the x-coordinate
     * @param y            the y-coordinate
     * @param isDeleted    the deletion status
     */
    public Coordinate(Integer coordinateId, int x, int y, boolean isDeleted) {
        this.coordinateId = coordinateId;
        this.x = x;
        this.y = y;
        this.isDeleted = isDeleted;
    }

    /**
     * Constructs a new Coordinate object with the specified x-coordinate and y-coordinate.
     *
     * @param x the x-coordinate
     * @param y the y-coordinate
     */
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Default constructor for Coordinate.
     */
    public Coordinate() {
    }

    /**
     * Returns the coordinate ID.
     *
     * @return the coordinate ID
     */
    public Integer getCoordinateId() {
        return coordinateId;
    }

    /**
     * Sets the coordinate ID.
     *
     * @param coordinateId the coordinate ID to set
     */
    public void setCoordinateId(Integer coordinateId) {
        this.coordinateId = coordinateId;
    }

    /**
     * Returns the x-coordinate.
     *
     * @return the x-coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Sets the x-coordinate.
     *
     * @param x the x-coordinate to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Returns the y-coordinate.
     *
     * @return the y-coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the y-coordinate.
     *
     * @param y the y-coordinate to set
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Returns the Mistake associated with the Coordinate.
     *
     * @return the Mistake associated with the Coordinate
     */
    public Mistake getMistake() {
        return mistake;
    }

    /**
     * Sets the Mistake associated with the Coordinate.
     *
     * @param mistake the Mistake to set
     */
    public void setMistake(Mistake mistake) {
        this.mistake = mistake;
    }

    /**
     * Returns the deletion status of the Coordinate.
     *
     * @return the deletion status
     */
    public boolean isDeleted() {
        return isDeleted;
    }

    /**
     * Sets the deletion status of the Coordinate.
     *
     * @param deleted the deletion status to set
     */
    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
