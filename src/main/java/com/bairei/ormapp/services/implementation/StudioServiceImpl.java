package com.bairei.ormapp.services.implementation;

import com.bairei.ormapp.models.Album;
import com.bairei.ormapp.models.Studio;
import com.bairei.ormapp.repositories.StudioRepository;
import com.bairei.ormapp.services.AlbumService;
import com.bairei.ormapp.services.StudioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class StudioServiceImpl implements StudioService {

    private final StudioRepository studioRepository;
    private final AlbumService albumService;

    public StudioServiceImpl(StudioRepository studioRepository, AlbumService albumService) {
        this.studioRepository = studioRepository;
        this.albumService = albumService;
    }

    @Override
    public Studio save(Studio studio) {
        return studioRepository.save(studio);
    }

    @Override
    public void saveOrUpdate(Studio studio) {
        studioRepository.saveOrUpdate(studio);
    }

    @Override
    public List<Studio> findAll() {
        return studioRepository.findAll();
    }

    @Override
    public Studio findById(Long id) {
        return studioRepository.findById(id);
    }

    @Override
    @Transactional
    public void deleteById(Long aLong) {
        Studio studio = studioRepository.findById(aLong);
        if (studio != null) {
            List<Album> albums = albumService.findAlbumsByStudioNameEqualsIgnoreCase(studio.getName());
            for (Album album : albums) {
                Set<Studio> studioSet = album.getStudios();
                studioSet.remove(studio);
                album.setStudios(studioSet);
                albumService.save(album);
            }
            studio.setLocation(null);
            studioRepository.deleteById(aLong);
        }
    }

    @Override
    public Integer count() {
        return studioRepository.count();
    }

    @Override
    public List<Studio> findStudiosByLocationPlaceEqualsIgnoreCase(String place) {
        return studioRepository.findStudiosByLocationPlaceEqualsIgnoreCase(place);
    }

}
