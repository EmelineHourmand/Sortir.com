package fr.eni.sortircom.bo;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table
public class Participant implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

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

    public Participant() {
    }

    public Participant(String lastname, String firstname, int phone, String mail, boolean adminstrator, boolean actif) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.phone = phone;
        this.mail = mail;
        this.adminstrator = adminstrator;
        this.actif = actif;
    }
}
