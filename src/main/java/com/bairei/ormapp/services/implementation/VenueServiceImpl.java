package com.bairei.ormapp.services.implementation;

import com.bairei.ormapp.models.Event;
import com.bairei.ormapp.models.Venue;
import com.bairei.ormapp.repositories.VenueRepository;
import com.bairei.ormapp.services.EventService;
import com.bairei.ormapp.services.VenueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class VenueServiceImpl implements VenueService {

    private final VenueRepository venueRepository;
    private final EventService eventService;

    public VenueServiceImpl(VenueRepository venueRepository, EventService eventService) {
        this.venueRepository = venueRepository;
        this.eventService = eventService;
    }

    @Override
    public Venue save(Venue venue) {
        return venueRepository.save(venue);
    }

    @Override
    public void saveOrUpdate(Venue venue) {
        venueRepository.saveOrUpdate(venue);
    }

    @Override
    public List<Venue> findAll() {
        return venueRepository.findAll();
    }

    @Override
    public Venue findById(Long id) {
        return venueRepository.findById(id);
    }

    @Override
    public void deleteById(Long aLong) {
        Venue venue = venueRepository.findById(aLong);
        if (venue != null){
            List<Event> events = eventService.findEventsByVenueNameEqualsIgnoreCase(venue.getName());
            for (Event event: events){
                eventService.deleteById(event.getId());
            }
            venueRepository.deleteById(aLong);
        }
    }

    @Override
    public Integer count() {
        return venueRepository.count();
    }

    @Override
    public List<Venue> findVenuesByLocationPlaceEqualsIgnoreCase(String place) {
        return venueRepository.findVenuesByLocationPlaceEqualsIgnoreCase(place);
    }
}
