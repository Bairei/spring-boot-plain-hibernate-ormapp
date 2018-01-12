package com.bairei.ormapp.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Band {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Band's name must not be null!")
    @Size(min = 1, message = "Band's name must not be null!")
    private String name;

    @NotNull @Min(value = 1900, message = "Year founded must be later than 1900!") @Max(value = 2018, message = "year founded must be not later than 2018!")
    private Integer yearFounded;

    @Min(value = 1900, message = "Year disbanded must be later than 1900!") @Max(value = 2018, message = "Year disbanded must be not later than 2018!")
    private Integer yearDisbanded;

    @ManyToOne
    private Genre genre;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "band_member", joinColumns = @JoinColumn(name = "band_id"),
            inverseJoinColumns = @JoinColumn(name = "member_id"))
    private Set<Member> members = new HashSet<>();

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "band")
    @JsonBackReference
    private Set<Album> albums = new HashSet<>();

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

    public Integer getYearDisbanded() {
        return yearDisbanded;
    }

    public void setYearDisbanded(Integer yearDisbanded) {
        this.yearDisbanded = yearDisbanded;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Set<Member> getMembers() {
        return members;
    }

    public void setMembers(Set<Member> members) {
        this.members = members;
    }

    public Set<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }

    @Override
    public String toString() {
        return this.getId().toString();
    }
}
