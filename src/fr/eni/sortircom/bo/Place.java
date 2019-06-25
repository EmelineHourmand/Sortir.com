package fr.eni.sortircom.bo;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;



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

    @Column(name = "street", length = 255)
    private String street;

    @Column(name = "latitude")
    private float latitude;

    @Column(name = "longitude")
    private float longitude;

    /**
     * Empty constructor
     */
    public Place(){
    }

    /**
     * Constructor
     * @param idPlace
     * @param name
     * @param street
     * @param latitude
     * @param longitude
     */
    public Place(int idPlace, String name, String street, float latitude, float longitude) {
        this.idPlace = idPlace;
        this.name = name;
        this.street = street;
        this.latitude = latitude;
        this.longitude = longitude;
    }

}
