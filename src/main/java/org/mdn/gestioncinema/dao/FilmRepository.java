package org.mdn.gestioncinema.dao;

import org.mdn.gestioncinema.Entities.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin(origins = "*")
public interface FilmRepository extends JpaRepository<Film,Long> {
}
