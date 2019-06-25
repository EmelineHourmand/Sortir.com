package fr.eni.sortircom.bo;


import lombok.Data;

import javax.persistence.*;

/**
 * @author jbruneau2019
 * Entity City
 */
@Data
@Entity
@Table(name = "CITIES")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ville")
    private int idVille;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "postal_code", length = 5)
    private String postalCode;

    @OneToMany
    private Place[] places;

}
