package fr.eni.sortircom.bo;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;

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

    @Column(name = "name")
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
    private String description;

    @Column(name = "state")
    private Character state;

    public Event() {
    }

    public Event(String name, LocalDateTime eventBeginning, LocalDateTime duration, LocalDateTime registrationLimit, Integer maxRegistration, String description, Character state) {
        this.name = name;
        this.eventBeginning = eventBeginning;
        this.duration = duration;
        this.registrationLimit = registrationLimit;
        this.maxRegistration = maxRegistration;
        this.description = description;
        this.state = state;
    }
}

