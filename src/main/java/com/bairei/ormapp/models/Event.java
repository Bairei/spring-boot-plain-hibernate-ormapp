package com.bairei.ormapp.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate eventDate;

    @Enumerated(value = EnumType.STRING)
    private EventType type;

    @ManyToOne(cascade = CascadeType.ALL)
    private Promoter promoter;

    @ManyToOne(cascade = CascadeType.ALL)
    private Venue venue;

    @ManyToMany
    @JoinTable(name = "band_event", joinColumns = @JoinColumn(name = "band_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id"))
    private Set<Band> bandSet = new HashSet<>();

    public Event() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public Promoter getPromoter() {
        return promoter;
    }

    public void setPromoter(Promoter promoter) {
        this.promoter = promoter;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public Set<Band> getBandSet() {
        return bandSet;
    }

    public void setBandSet(Set<Band> bandSet) {
        this.bandSet = bandSet;
    }
}
