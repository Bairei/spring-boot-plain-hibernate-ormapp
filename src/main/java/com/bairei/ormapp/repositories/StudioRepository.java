package com.bairei.ormapp.repositories;

import com.bairei.ormapp.models.Studio;

import java.util.List;

public interface StudioRepository extends GenericRepository<Studio, Long>{
    List<Studio> findStudiosByLocationPlaceIncluding(String place);
    List<Studio> findStudiosByLocationPlaceEqualsIgnoreCase(String place);
}
