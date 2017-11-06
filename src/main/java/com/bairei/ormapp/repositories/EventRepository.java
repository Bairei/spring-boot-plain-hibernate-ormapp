package com.bairei.ormapp.repositories;

import com.bairei.ormapp.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
