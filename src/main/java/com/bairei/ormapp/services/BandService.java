package com.bairei.ormapp.services;

import com.bairei.ormapp.models.Band;

import java.util.List;

public interface BandService {
    Band save(Band band);
    void saveOrUpdate(Band band);
    List<Band> findAll();
    Band findById(Long id);
    void deleteById(Long aLong);
    Integer count();
    List<Band> findBandsByNameIncluding(String name);
}
