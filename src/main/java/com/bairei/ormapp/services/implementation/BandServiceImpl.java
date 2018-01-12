package com.bairei.ormapp.services.implementation;

import com.bairei.ormapp.models.Album;
import com.bairei.ormapp.models.Band;
import com.bairei.ormapp.models.Event;
import com.bairei.ormapp.repositories.BandRepository;
import com.bairei.ormapp.services.AlbumService;
import com.bairei.ormapp.services.EventService;
import com.bairei.ormapp.services.BandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class BandServiceImpl implements BandService {

    private final BandRepository bandRepository;
    private final EventService eventService;
    private final AlbumService albumService;

    public BandServiceImpl(BandRepository bandRepository, EventService eventService, AlbumService albumService) {
        this.bandRepository = bandRepository;
        this.eventService = eventService;
        this.albumService = albumService;
    }

    @Override
    public Band save(Band band) {
        return bandRepository.save(band);
    }

    @Override
    public void saveOrUpdate(Band band) {
        bandRepository.saveOrUpdate(band);
    }

    @Override
    @Transactional
    public List<Band> findAll() {
        return bandRepository.findAll();
    }

    @Override
    public Band findById(Long id) {
        return bandRepository.findById(id);
    }

    @Override
    @Transactional
    public void deleteById(Long aLong) {
        Band band = bandRepository.findById(aLong);
        if (band != null) {
            band.setGenre(null);
            band.setMembers(new HashSet<>());
            bandRepository.save(band);
            for (Event event : eventService.findEventsByBandsIncludingBandNameEqualsIgnoreCase(band.getName())) {
                if (event.getBandSet().contains(band)) {
                    Set<Band> bands = event.getBandSet();
                    bands.remove(band);
                    event.setBandSet(bands);
                    if (event.getBandSet().size() < 1) {
                        eventService.deleteById(event.getId());
                    } else {
                        eventService.save(event);
                    }
                }
            }
//            for (Album a: band.getAlbums()){
//                albumService.deleteById(a.getId());
//            }
            bandRepository.deleteById(aLong);
        }
    }

    @Override
    public Integer count() {
        return bandRepository.count();
    }

    @Override
    public List<Band> findBandsByMembersIncludingMemberNameEqualsIgnoreCase(String name) {
        return bandRepository.findBandsByMembersIncludingMemberNameEqualsIgnoreCase(name);
    }

    @Override
    public List<Band> findBandsByGenreNameEqualsIgnoreCase(String name) {
        return bandRepository.findBandsByGenreNameEqualsIgnoreCase(name);
    }
}
