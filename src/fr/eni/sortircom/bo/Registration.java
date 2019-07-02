package fr.eni.sortircom.bo;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Emeline Hourmand
 */
@Data
@Entity
@Table(name = "REGISTRATIONS")
public class Registration implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_registration")
    private long idRegistration;

    @Column(name = "date_registration", nullable = false)
    private LocalDateTime dateRegistration;

    @ManyToOne
    @JoinColumn(name = "id_participant", nullable = false)
    private Participant participant;

    @ManyToOne
    @JoinColumn(name = "id_event", nullable = false)
    private Event event;

    public Registration(LocalDateTime dateRegistration) {
        this.dateRegistration = dateRegistration;
    }

    public Registration() {}
}
