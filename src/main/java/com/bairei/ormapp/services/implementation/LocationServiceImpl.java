package com.bairei.ormapp.services.implementation;

import com.bairei.ormapp.models.Location;
import com.bairei.ormapp.models.Studio;
import com.bairei.ormapp.models.Venue;
import com.bairei.ormapp.repositories.LocationRepository;
import com.bairei.ormapp.services.LocationService;
import com.bairei.ormapp.services.StudioService;
import com.bairei.ormapp.services.VenueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;
    private final StudioService studioService;
    private final VenueService venueService;

    public LocationServiceImpl(LocationRepository locationRepository, StudioService studioService, VenueService venueService) {
        this.locationRepository = locationRepository;
        this.studioService = studioService;
        this.venueService = venueService;
    }

    @Override
    public Location save(Location location) {
        return locationRepository.save(location);
    }

    @Override
    public void saveOrUpdate(Location location) {
        locationRepository.saveOrUpdate(location);
    }

    @Override
    public List<Location> findAll() {
        return locationRepository.findAll();
    }

    @Override
    public Location findById(Long id) {
        return locationRepository.findById(id);
    }

    @Override
    public void deleteById(Long aLong) {
        Location location = locationRepository.findById(aLong);
        if (location != null){
            List<Studio> studios = studioService.findStudiosByLocationPlaceEqualsIgnoreCase(location.getPlace());
            for (Studio studio: studios){
                studioService.deleteById(studio.getId());
            }
            List<Venue> venues = venueService.findVenuesByLocationPlaceEqualsIgnoreCase(location.getPlace());
            for (Venue venue: venues){
                venueService.deleteById(venue.getId());
            }
            locationRepository.deleteById(aLong);
        }
    }

    @Override
    public Integer count() {
        return locationRepository.count();
    }
}
