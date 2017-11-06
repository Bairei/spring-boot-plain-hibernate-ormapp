package com.bairei.ormapp.repositories;

import com.bairei.ormapp.models.Venue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VenueRepository extends JpaRepository<Venue, Long>{
}
