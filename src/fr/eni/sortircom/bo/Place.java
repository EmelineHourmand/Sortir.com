package fr.eni.sortircom.bo;

import lombok.Data;

import javax.persistence.*;
import javax.persistence.GenerationType;
import java.io.Serializable;


/**
 * @author jbruneau2019
 * @author Emeline Hourmand
 */
@Data
@Entity
@Table(name = "PLACES")
public class Place implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_place")
    private long idPlace;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "street")
    private String street;

    @Column(name = "latitude")
    private float latitude;

    @Column(name = "longitude")
    private float longitude;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_city", nullable = false)
    private City city;


    /**
     * Empty constructor
     */
    public Place(){}

    /**
     * Constructor
     * @param name
     * @param street
     * @param latitude
     * @param longitude
     */
    public Place(String name, String street, float latitude, float longitude, City city) {
        this.idPlace = idPlace;
        this.name = name;
        this.street = street;
        this.latitude = latitude;
        this.longitude = longitude;
        this.city = city;
    }

    /**
     *
     * @param name
     * @param street
     * @param latitude
     * @param longitude
     */
    public Place(String name, String street, float latitude, float longitude) {
        this.name = name;
        this.street = street;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
