package com.cvqs.mistakeservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.ArrayList;
import java.util.List;

/**
 * Entity class representing a Vehicle.
 */
@Table(name = "vehicles")
@Entity
@Builder
@AllArgsConstructor
@SQLDelete(sql = "UPDATE vehicles SET is_deleted = true WHERE id=id")
@Where(clause = "is_deleted=false")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "chassis_number")
    private String chassisNumber;

    @Column(name = "band_id")
    private int bandId;

    @Column(name = "factory_name")
    private String factoryName;

    @Column(name = "is_deleted")
    private Boolean isDeleted = Boolean.FALSE;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    List<Mistake> mistakes = new ArrayList<>();

    /**
     * Constructs a new Vehicle object with the specified ID, chassis number, band ID, factory name, and list of mistakes.
     *
     * @param id            the ID
     * @param chassisNumber the chassis number
     * @param bandId        the band ID
     * @param factoryName   the factory name
     * @param mistakes      the list of mistakes
     */
    public Vehicle(int id, String chassisNumber, int bandId, String factoryName, List<Mistake> mistakes) {
        this.id = id;
        this.chassisNumber = chassisNumber;
        this.bandId = bandId;
        this.factoryName = factoryName;
        this.mistakes = mistakes;
    }

    /**
     * Default constructor for Vehicle.
     */
    public Vehicle() {
    }

    /**
     * Returns the ID of the Vehicle.
     *
     * @return the ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the Vehicle.
     *
     * @param id the ID to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the chassis number of the Vehicle.
     *
     * @return the chassis number
     */
    public String getChassisNumber() {
        return chassisNumber;
    }

    /**
     * Sets the chassis number of the Vehicle.
     *
     * @param chassisNumber the chassis number to set
     */
    public void setChassisNumber(String chassisNumber) {
        this.chassisNumber = chassisNumber;
    }

    /**
     * Returns the band ID of the Vehicle.
     *
     * @return the band ID
     */
    public int getBandId() {
        return bandId;
    }

    /**
     * Sets the band ID of the Vehicle.
     *
     * @param bandId the band ID to set
     */
    public void setBandId(int bandId) {
        this.bandId = bandId;
    }

    /**
     * Returns the list of mistakes associated with the Vehicle.
     *
     * @return the list of mistakes
     */
    public List<Mistake> getMistakes() {
        return mistakes;
    }

    /**
     * Sets the list of mistakes associated with the Vehicle.
     *
     * @param mistakes the list of mistakes to set
     */
    public void setMistakes(List<Mistake> mistakes) {
        this.mistakes = mistakes;
    }

    /**
     * Returns the deletion status of the Vehicle.
     *
     * @return the deletion status
     */
    public Boolean getDeleted() {
        return isDeleted;
    }

    /**
     * Sets the deletion status of the Vehicle.
     *
     * @param deleted the deletion status to set
     */
    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    /**
     * Returns the factory name of the Vehicle.
     *
     * @return the factory name
     */
    public String getFactoryName() {
        return factoryName;
    }

    /**
     * Sets the factory name of the Vehicle.
     *
     * @param factoryName the factory name to set
     */
    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }
}
