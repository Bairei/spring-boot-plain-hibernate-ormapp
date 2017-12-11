package com.bairei.ormapp.services;

import com.bairei.ormapp.models.Band;
import com.bairei.ormapp.models.Genre;

import java.util.List;

public interface GenreService extends GenericService<Genre>{
    Band recommendABand(Long id);
}
