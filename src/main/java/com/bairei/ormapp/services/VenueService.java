package com.bairei.ormapp.services;

import com.bairei.ormapp.models.Venue;

import java.util.List;

public interface VenueService extends GenericService<Venue> {
    List<Venue> findVenuesByLocationPlaceEqualsIgnoreCase(String place);

}
