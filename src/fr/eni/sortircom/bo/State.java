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

    @Column(name = "label", length = 50, nullable = false)
    private String label;

    /**
     * Constructor
     * @param label
     */
    public State(String label) {
        this.label = label;
    }

    /**
     * Empty Constructor
     */
    public State() {}
}
