package fr.eni.sortircom.bo;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Entity
@Table
/**
 *
 * @author hWasier
 *
 */
public class Event implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String name;

    @Column
    private LocalDateTime eventBeginning;

    @Column
    private LocalTime duration;

    @Column
    private LocalDateTime registrationLimit;

    @Column
    private Integer maxRegistration;

    @Column
    private String eventDescription;

    @Column
    private Character state;

    public Event() {
    }

    public Event(String name, LocalDateTime eventBeginning, LocalTime duration, LocalDateTime registrationLimit, Integer maxRegistration, String eventDescription, Character state) {
        this.name = name;
        this.eventBeginning = eventBeginning;
        this.duration = duration;
        this.registrationLimit = registrationLimit;
        this.maxRegistration = maxRegistration;
        this.eventDescription = eventDescription;
        this.state = state;
    }
}

