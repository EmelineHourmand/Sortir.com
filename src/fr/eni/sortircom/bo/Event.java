package fr.eni.sortircom.bo;


import lombok.Data;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author eHourmand2019
 * Entity Event
 */
@Data
@Entity
@Table(name = "EVENTS")
public class Event implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_event")
    private Long idEvent;

    @Column(name = "name", length = 250)
    private String name;

    @Column(name = "event_beginning")
    private LocalDateTime eventBeginning;

    @Column(name = "duration")
    private LocalDateTime duration;

    @Column(name = "registration_limit")
    private LocalDateTime registrationLimit;

    @Column(name = "max_registration")
    private Integer maxRegistration;

    @Column(name = "description")
    @Type(type = "text")
    private String description;

    @ManyToOne
    @JoinColumn(name = "id_place")
    private Place place;

    @ManyToOne
    @JoinColumn(name = "id_state")
    private State state;

    @ManyToOne
    @JoinColumn(name = "id_site")
    private Site site;

    @ManyToOne
    @JoinColumn(name = "id_organizer")
    private Participant organizer;

    /**
     * Empty Constructor
     */
    public Event() {
    }


}

