package com.bairei.ormapp.repositories;

import com.bairei.ormapp.models.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long>{
}
