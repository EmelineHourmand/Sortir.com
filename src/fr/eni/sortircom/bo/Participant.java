package fr.eni.sortircom.bo;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "PARTICIPANTS")
/**
 *
 * @author hWasier2019
 * Entity Participant
 *
 */
public class Participant implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_participant")
    private Long idParticipant;

    @Column(name = "lastname", length = 25)
    private String lastname;

    @Column(name = "firstname", length = 25)
    private String firstname;

    @Column(name = "phone")
    private int phone;

    @Column(name = "mail", length = 30)
    private String mail;

    @Column(name = "administrator")
    private boolean adminstrator;

    @Column(name = "actif")
    private boolean actif;


    private Site site;

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
