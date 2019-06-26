package fr.eni.sortircom.bo;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "SITES")

public class Site implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_site")
    private Long idSite;

    @Column(name = "name", length = 50)
    private String name;

    /**
     *
     * @param name
     */
    public Site(String name) {
        this.name = name;
    }

    /**
     * Empty constructor
     */
    public Site() {
    }
}
