package com.bairei.ormapp.repositories;

import com.bairei.ormapp.models.Band;

import java.util.List;

public interface BandRepository extends GenericRepository<Band, Long> {
    List<Band> findBandsByGenreNameEqualsIgnoreCase(String genreName);
    List<Band> findBandsByMembersIncludingMemberNameEqualsIgnoreCase(String name);
}
