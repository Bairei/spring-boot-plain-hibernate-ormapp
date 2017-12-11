package com.bairei.ormapp.services.implementation;

import com.bairei.ormapp.models.Album;
import com.bairei.ormapp.models.Label;
import com.bairei.ormapp.repositories.LabelRepository;
import com.bairei.ormapp.services.AlbumService;
import com.bairei.ormapp.services.LabelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class LabelServiceImpl implements LabelService {

    private final LabelRepository labelRepository;
    private final AlbumService albumService;

    public LabelServiceImpl(LabelRepository labelRepository, AlbumService albumService) {
        this.labelRepository = labelRepository;
        this.albumService = albumService;
    }

    @Override
    public Label save(Label label) {
        return labelRepository.save(label);
    }

    @Override
    public void saveOrUpdate(Label label) {
        labelRepository.saveOrUpdate(label);
    }

    @Override
    public List<Label> findAll() {
        return labelRepository.findAll();
    }

    @Override
    public Label findById(Long id) {
        return labelRepository.findById(id);
    }

    @Override
    public void deleteById(Long aLong) {
        Label label = labelRepository.findById(aLong);
        if (label != null){
            List<Album> albums = albumService.findAlbumsByLabelNameEqualsIgnoreCase(label.getName());
            for (Album album: albums){
                albumService.deleteById(album.getId());
            }
            labelRepository.deleteById(aLong);
        }
    }

    @Override
    public Integer count() {
        return labelRepository.count();
    }
}
