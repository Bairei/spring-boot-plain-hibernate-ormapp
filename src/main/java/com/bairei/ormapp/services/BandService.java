package com.bairei.ormapp.services;

import com.bairei.ormapp.models.Band;

import java.util.List;

public interface BandService extends GenericService<Band>{
    List<Band> findBandsByNameIncluding(String name);
    List<Band> findBandsByMembersIncludingMemberNameEqualsIgnoreCase(String name);
    List<Band> findBandsByGenreNameEqualsIgnoreCase(String name);
}
