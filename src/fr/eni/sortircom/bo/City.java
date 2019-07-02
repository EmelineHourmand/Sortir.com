package fr.eni.sortircom.bo;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author jbruneau2019
 * @author Emeline Hourmand
 */
@Data
@Entity
@Table(name = "CITIES")
public class City implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ville")
    private long idVille;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "postal_code", length = 5, nullable = false)
    private String postalCode;


    /**
     * Empty Constructor
     */
    public City() {}

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
