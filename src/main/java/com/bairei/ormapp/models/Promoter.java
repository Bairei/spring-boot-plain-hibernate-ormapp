package com.bairei.ormapp.models;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class Promoter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty (message = "Promoter's name must not be empty!")
    @NotNull(message = "Promoter's name must not be null!")
    private String name;

    @Min(value = 1900, message = "Promoter's year of foundation must be not earlier than 1900!")
    @Max(value = 2018, message = "Promoter's year of foundation must be not later than 2018!")
    private Integer yearFounded;

    public Promoter() {
    }

    public Promoter(String name, Integer yearFounded) {
        this.name = name;
        this.yearFounded = yearFounded;
    }

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
}
