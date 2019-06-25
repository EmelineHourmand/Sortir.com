package fr.eni.sortircom.bo;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author jbruneau2019
 * Entity City
 */
@Data
@Entity
@Table(name = "CITIES")
public class City implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ville")
    private int idVille;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "postal_code", length = 5)
    private String postalCode;


    /**
     * Empty Constructor
     */
    public City() {
    }

    /**
     * Constructor
     * @param name
     * @param postalCode
     */
    public City(String name, String postalCode) {
        this.name = name;
        this.postalCode = postalCode;
    }
}
