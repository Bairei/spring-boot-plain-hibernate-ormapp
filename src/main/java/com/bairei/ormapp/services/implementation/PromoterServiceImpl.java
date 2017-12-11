package com.bairei.ormapp.services.implementation;

import com.bairei.ormapp.models.Event;
import com.bairei.ormapp.models.Promoter;
import com.bairei.ormapp.repositories.PromoterRepository;
import com.bairei.ormapp.services.EventService;
import com.bairei.ormapp.services.PromoterService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromoterServiceImpl implements PromoterService {

    private final PromoterRepository promoterRepository;
    private final EventService eventService;

    public PromoterServiceImpl(PromoterRepository promoterRepository, EventService eventService) {
        this.promoterRepository = promoterRepository;
        this.eventService = eventService;
    }

    @Override
    public Promoter save(Promoter promoter) {
        return promoterRepository.save(promoter);
    }

    @Override
    public void saveOrUpdate(Promoter promoter) {
        promoterRepository.saveOrUpdate(promoter);
    }

    @Override
    public List<Promoter> findAll() {
        return promoterRepository.findAll();
    }

    @Override
    public Promoter findById(Long id) {
        return promoterRepository.findById(id);
    }

    @Override
    public void deleteById(Long aLong) {
        Promoter promoter = promoterRepository.findById(aLong);
        if (promoter != null){
            List<Event> events = eventService.findEventsByPromoterNameEqualsIgnoreCase(promoter.getName());
            for (Event event: events){
                eventService.deleteById(event.getId());
            }
            promoterRepository.deleteById(aLong);
        }
    }

    @Override
    public Integer count() {
        return promoterRepository.count();
    }
}
