package com.bairei.ormapp.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
public class Label {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Label's name must not be null!")
    @Size(min = 1, message = "Label's name must not be empty!")
    private String name;

    @Min(value = 1900, message = "Label's year of foundation must not be earlier than 1900!")
    @Max(value = 2017, message = "Label's year of foundation must not be later than 2017!")
    private Integer yearFounded;

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

    public Integer getYearFounded() {
        return yearFounded;
    }

    public void setYearFounded(Integer yearFounded) {
        this.yearFounded = yearFounded;
    }

    @Override
    public String toString() {
        return this.name + ", " + this.yearFounded;
    }
}
