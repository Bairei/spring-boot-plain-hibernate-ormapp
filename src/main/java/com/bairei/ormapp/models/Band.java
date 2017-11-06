package com.bairei.ormapp.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Band {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    private String name;

    @Min(1900) @Max(2017)
    private Integer yearFounded;

    @Min(1900) @Max(2017)
    private Integer yearDisbanded;

    @ManyToOne(cascade = CascadeType.ALL)
    private Genre genre;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "band_member", joinColumns = @JoinColumn(name = "band_id"),
            inverseJoinColumns = @JoinColumn(name = "member_id"))
    private Set<Member> members = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "band")
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
        return this.id + " " + this.name;
    }
}
