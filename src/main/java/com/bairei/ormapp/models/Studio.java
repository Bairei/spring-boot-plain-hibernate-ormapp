package com.bairei.ormapp.models;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Studio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Studio name must not be empty!")
    @NotNull(message = "Studio name must not be null!")
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    private Location location;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Studio() {
    }

    public Studio(String name, Location location) {
        this.name = name;
        this.location = location;
    }

    @Override
    public String toString() {
        return this.getId().toString();
    }
}
