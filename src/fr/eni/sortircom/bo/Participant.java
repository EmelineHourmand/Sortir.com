package fr.eni.sortircom.bo;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table
/**
 *
 * @author ehourman2019
 * Entity Participant
 *
 */
public class Participant implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String lastname;

    @Column
    private String firstname;

    @Column
    private int phone;

    @Column
    private String mail;

    @Column
    private boolean adminstrator;

    @Column
    private boolean actif;

    /**
     * Empty constructor
     */
    public Participant() {
    }

    /**
     * Constructor
     * @param lastname
     * @param firstname
     * @param phone
     * @param mail
     * @param adminstrator
     * @param actif
     */
    public Participant(String lastname, String firstname, int phone, String mail, boolean adminstrator, boolean actif) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.phone = phone;
        this.mail = mail;
        this.adminstrator = adminstrator;
        this.actif = actif;
    }
}
