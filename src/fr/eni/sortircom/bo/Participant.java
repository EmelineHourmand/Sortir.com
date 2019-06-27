package fr.eni.sortircom.bo;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author hWasier2019
 * @author Emeline Hourmand
 */
@Data
@Entity
@Table(name = "PARTICIPANTS")
public class Participant implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_participant")
    private Long idParticipant;

    @Column(name = "username", length = 50, nullable = false)
    private String username;

    @Column(name = "lastname", length = 50, nullable = false)
    private String lastname;

    @Column(name = "firstname", length = 50, nullable = false)
    private String firstname;

    @Column(name = "phone", length = 15)
    private String phone;

    @Column(name = "mail", length = 50, unique = true, nullable = false)
    private String mail;

    @Column(name = "administrator", nullable = false)
    private boolean adminstrator;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "actif", nullable = false)
    private boolean actif;

    @ManyToOne
    @JoinColumn(name = "id_site", nullable = false)
    private Site site;

    /**
     * Empty constructor
     */
    public Participant() {}

    /**
     * Constructor
     * @param username
     * @param lastname
     * @param firstname
     * @param phone
     * @param mail
     * @param adminstrator
     * @param password
     * @param actif
     * @param site
     */
    public Participant(String username, String lastname, String firstname, String phone, String mail,
                       boolean adminstrator, String password, boolean actif, Site site) {
        this.username = username;
        this.lastname = lastname;
        this.firstname = firstname;
        this.phone = phone;
        this.mail = mail;
        this.adminstrator = adminstrator;
        this.password = password;
        this.actif = actif;
        this.site = site;
    }
}
