package fr.eni.sortircom.bo;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Emeline Hourmand
 */
@Data
@Entity
@Table(name = "STATES")
public class State implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_state")
    private Long idState;

    @Column(name = "wording", length = 50)
    private String wording;

    /**
     * Constructor
     * @param wording
     */
    public State(String wording) {
        this.wording = wording;
    }

    /**
     * Empty Constructor
     */
    public State() {
    }


}
