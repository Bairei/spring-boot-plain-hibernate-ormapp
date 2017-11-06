package com.bairei.ormapp.repositories;

import com.bairei.ormapp.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends GenericRepository<Genre, Long> {
}
