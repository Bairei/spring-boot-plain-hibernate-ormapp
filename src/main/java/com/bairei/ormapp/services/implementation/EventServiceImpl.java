package com.bairei.ormapp.services.implementation;

import com.bairei.ormapp.models.Event;
import com.bairei.ormapp.repositories.EventRepository;
import com.bairei.ormapp.services.EventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    public EventServiceImpl (EventRepository eventRepository){
        this.eventRepository = eventRepository;
    }

    @Override
    public Event save(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public void saveOrUpdate(Event event) {
        eventRepository.save(event);
    }

    @Override
    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    @Override
    public Event findById(Long id) {
        return eventRepository.findById(id);
    }

    @Override
    public void deleteById(Long aLong) {
        eventRepository.deleteById(aLong);
    }

    @Override
    public Integer count() {
        return eventRepository.count();
    }

    @Override
    public List<Event> list5UpcomingEvents() {
        //TODO
        List<Event> allEvents = findAll();
        log.info(allEvents.toString());
        return allEvents.stream()
                .filter(event -> event.getEventDate().isAfter(LocalDate.now()))
                .sorted(Comparator.comparing(Event::getEventDate, Comparator.nullsLast(Comparator.naturalOrder())))
                .limit(5)
                .collect(Collectors.toList());
    }

    @Override
    public List<Event> findEventsByVenueNameEqualsIgnoreCase(String name) {
        return eventRepository.findEventsByVenueNameEqualsIgnoreCase(name);
    }

    @Override
    public List<Event> findEventsByPromoterNameEqualsIgnoreCase(String name) {
        return eventRepository.findEventsByPromoterNameEqualsIgnoreCase(name);
    }

    @Override
    public List<Event> findEventsByBandsIncludingBandNameEqualsIgnoreCase(String name) {
        return eventRepository.findEventsByBandsIncludingBandNameEqualsIgnoreCase(name);
    }
}
