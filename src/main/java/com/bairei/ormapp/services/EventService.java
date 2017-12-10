package com.bairei.ormapp.services;

import com.bairei.ormapp.models.Event;

import java.util.List;

public interface EventService {
    Event save(Event event);
    void saveOrUpdate(Event event);
    List<Event> findAll();
    Event findById(Long id);
    void deleteById(Long aLong);
    Integer count();
    List<Event> list5UpcomingEvents();
}
