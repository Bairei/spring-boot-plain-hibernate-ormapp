package com.bairei.ormapp.services;

import com.bairei.ormapp.models.Event;

import java.util.List;

public interface EventService extends GenericService<Event>{
    List<Event> list5UpcomingEvents();

    List<Event> findEventsByVenueNameEqualsIgnoreCase(String name);
}
