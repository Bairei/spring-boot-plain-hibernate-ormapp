package com.bairei.ormapp.services;

import com.bairei.ormapp.models.Band;
import com.bairei.ormapp.repositories.BandRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BandServiceImpl implements BandService {

    private final BandRepository bandRepository;

    public BandServiceImpl(BandRepository bandRepository) {
        this.bandRepository = bandRepository;
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
        return bandRepository.listAll();
    }

    @Override
    public Band findById(Long id) {
        return bandRepository.findById(id);
    }

    @Override
    public void deleteById(Long aLong) {
        bandRepository.deleteById(aLong);
    }

    @Override
    public Integer count() {
        return bandRepository.count();
    }
}
