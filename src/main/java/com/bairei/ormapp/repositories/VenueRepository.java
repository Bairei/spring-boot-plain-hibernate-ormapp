package com.bairei.ormapp.repositories;

import com.bairei.ormapp.models.Venue;

import java.util.List;

public interface VenueRepository extends GenericRepository<Venue, Long>{
    List<Venue> listVenuesByLocationPlaceIncluding(String place);
}
