package org.mdn.gestioncinema.dao;

import org.mdn.gestioncinema.Entities.Ville;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin(origins = "*")
public interface VilleRepository extends JpaRepository<Ville,Long> {
}
