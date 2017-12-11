package com.bairei.ormapp.services;

import com.bairei.ormapp.models.Studio;

import java.util.List;

public interface StudioService extends GenericService<Studio>{

    List<Studio> findStudiosByLocationPlaceEqualsIgnoreCase(String place);

    List<Studio> findStudiosByLocationPlaceIncluding(String place);
}
