package com.bairei.ormapp.repositories;

import com.bairei.ormapp.models.Event;

import java.util.List;

public interface EventRepository extends GenericRepository<Event, Long> {
    List<Event> findEventsByBandsIncludingBandNameEqualsIgnoreCase(String name);
    List<Event> findEventsByVenueNameEqualsIgnoreCase(String name);
    List<Event> findEventsByPromoterNameEqualsIgnoreCase(String name);
}
