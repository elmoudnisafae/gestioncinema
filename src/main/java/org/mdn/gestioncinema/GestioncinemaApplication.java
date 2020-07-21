package org.mdn.gestioncinema;

import org.mdn.gestioncinema.Entities.*;
import org.mdn.gestioncinema.service.ICinemaInitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class GestioncinemaApplication implements CommandLineRunner {

    @Autowired
    RepositoryRestConfiguration repositoryRestConfiguration;

    @Autowired
    private ICinemaInitService cinemaInitService;
    public static void main(String[] args) {
        SpringApplication.run(GestioncinemaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        repositoryRestConfiguration.exposeIdsFor(Film.class, Salle.class, Ticket.class);
cinemaInitService.initVilles();
cinemaInitService.initCinemas();
cinemaInitService.initSalles();
cinemaInitService.initPlaces();
cinemaInitService.initSeance();
cinemaInitService.initCategories();
cinemaInitService.initFilms();
cinemaInitService.initProjection();
cinemaInitService.initTicket();

    }
}
