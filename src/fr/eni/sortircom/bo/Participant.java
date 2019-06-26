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

    @Column(name = "lastname", length = 50)
    private String lastname;

    @Column(name = "firstname", length = 50)
    private String firstname;

    @Column(name = "phone")
    private String phone;

    @Column(name = "mail", length = 50, unique = true)
    private String mail;

    @Column(name = "administrator")
    private boolean adminstrator;

    @Column(name = "password")
    private String password;

    @Column(name = "actif")
    private boolean actif;

    private Site site;

    @ManyToMany private Event eventParticipator;
    @OneToMany private Event eventOrganisator;

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
     * @param password
     * @param actif
     */
    public Participant(String lastname, String firstname, String phone, String mail, boolean adminstrator, String password, boolean actif) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.phone = phone;
        this.mail = mail;
        this.adminstrator = adminstrator;
        this.password = password;
        this.actif = actif;
    }
}
