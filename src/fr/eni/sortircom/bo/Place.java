package fr.eni.sortircom.bo;

import lombok.Data;

import javax.persistence.*;
import javax.persistence.GenerationType;
import java.io.Serializable;


/**
 * @author jbruneau2019
 * Entity Place
 */
@Data
@Entity
@Table(name = "PLACES")
public class Place implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_place")
    private int idPlace;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "street")
    private String street;

    @Column(name = "latitude")
    private float latitude;

    @Column(name = "longitude")
    private float longitude;

    @ManyToOne
    @JoinColumn(name="id_city")
    private City city;


    /**
     * Empty constructor
     */
    public Place(){
    }

    /**
     * Constructor
     * @param name
     * @param street
     * @param latitude
     * @param longitude
     */
    public Place(String name, String street, float latitude, float longitude) {
        this.idPlace = idPlace;
        this.name = name;
        this.street = street;
        this.latitude = latitude;
        this.longitude = longitude;
    }

}
