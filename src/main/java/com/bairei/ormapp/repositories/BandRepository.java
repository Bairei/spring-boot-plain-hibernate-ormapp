package com.bairei.ormapp.repositories;

import com.bairei.ormapp.models.Band;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BandRepository extends GenericRepository<Band, Long> {
}
