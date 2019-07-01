package fr.eni.sortircom.bo;


import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author eHourmand2019
 */
@Data
@Entity
@Table(name = "EVENTS")
public class Event implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_event")
    private Long idEvent;

    @Column(name = "name", length = 250, nullable = false)
    private String name;

    @Column(name = "event_beginning", nullable = false)
    private LocalDateTime eventBeginning;

    @Column(name = "event_end")
    private LocalDateTime eventEnd;

    @Column(name = "registration_limit", nullable = false)
    private LocalDateTime registrationLimit;

    @Column(name = "max_registration", nullable = false)
    private Integer maxRegistration;

    @Column(name = "description")
    @Type(type = "text")
    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_place", nullable = false)
    private Place place;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_state", nullable = false)
    private State state;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_site", nullable = false)
    private Site site;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_organizer", nullable = false)
    private Participant organizer;

    /**
     * Empty Constructor
     */
    public Event() {}

    /**
     * Constructor
     * @param name
     * @param eventBeginning
     * @param eventEnd
     * @param registrationLimit
     * @param maxRegistration
     * @param description
     * @param place
     * @param state
     * @param site
     * @param organizer
     */
    public Event(String name, LocalDateTime eventBeginning, LocalDateTime eventEnd, LocalDateTime registrationLimit,
                 Integer maxRegistration, String description, Place place, State state, Site site, Participant organizer) {
        this.name = name;
        this.eventBeginning = eventBeginning;
        this.eventEnd = eventEnd;
        this.registrationLimit = registrationLimit;
        this.maxRegistration = maxRegistration;
        this.description = description;
        this.place = place;
        this.state = state;
        this.site = site;
        this.organizer = organizer;
    }
}

