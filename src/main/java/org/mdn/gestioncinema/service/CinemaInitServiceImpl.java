package org.mdn.gestioncinema.service;

import org.mdn.gestioncinema.Entities.*;
import org.mdn.gestioncinema.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

@Service
@Transactional
public class CinemaInitServiceImpl implements ICinemaInitService {
    @Autowired
    private VilleRepository villeRepository;
    @Autowired
    private CinemaRepository cinemaRepository;
    @Autowired
    private SalleRepository salleRepository;
    @Autowired
    private PlaceRepository placeRepository;
    @Autowired
    private SeanceRepository seanceRepository;
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private ProjectionRepository projectionRepository;
    @Autowired
    private CategorieRepository categorieRepository;
    @Autowired
    private TicketRepository ticketRepository;



    @Override
    public void initVilles() {
        Stream.of("Casablanca","Marakech","Fes","Tanger").forEach(nameVille->{
            Ville ville=new Ville();
            ville.setName(nameVille);
            villeRepository.save(ville );
        });
    }

    @Override
    public void initCinemas() {
villeRepository.findAll().forEach(v->{
    Stream.of("MegaRama","IMax","Founoun","Chahrazad").forEach(nameCinema->{
        Cinema cinema=new Cinema();
        cinema.setName(nameCinema);
        cinema.setNombreSalles(3+(int)(Math.random()*7));
        cinema.setVille(v);
        cinemaRepository.save(cinema);
    });
});
    }

    @Override
    public void initSalles() {
cinemaRepository.findAll().forEach(cinema -> {
    for (int i=0;i<cinema.getNombreSalles();i++){
        Salle salle=new Salle();
        salle.setName("Salle"+(i+1));
        salle.setCinema(cinema);
        salle.setNombrePlace(15+(int)(Math.random()*20));
        salleRepository.save(salle);
    }
});
    }



    @Override
    public void initPlaces() {
salleRepository.findAll().forEach(salle -> {
    for (int i=0;i<salle.getNombrePlace();i++){
        Place place=new Place();
        place.setNumero(i+1);
        place.setSalle(salle);
        placeRepository.save(place);
    }
});
    }
    @Override
    public void initSeance() {
        DateFormat dateFormat=new SimpleDateFormat("HH:mm");
Stream.of("12:00","15:00","17:00","19:00","21:00","23:00","00:00").forEach(s->{
    Seance seance=new Seance();
    try {
        seance.setHeureDebut(dateFormat.parse(s));
        seanceRepository.save(seance);
    } catch (ParseException e) {
        e.printStackTrace();
    }
});
    }
    @Override
    public void initCategories() {
Stream.of("Histoire","Drama","Romance","Anime","Comedie").forEach(cat->{
    Categorie categorie=new Categorie();
    categorie.setNom(cat);
    categorieRepository.save(categorie);
});

    }
    @Override
    public void initFilms() {
        double[]durees=new double[]{1,2,2.5,2.34,1.35};
        List<Categorie> categories=categorieRepository.findAll();
Stream.of("Ryoninkinshin","TheHalfOfIt","EatPrayLove","MemoriesOfGaisha","KimiNoNawa","KoeNokatachi").forEach(titreFilm->{
    Film film=new Film();
    film.setTitre(titreFilm);
    film.setDuree(durees[new Random().nextInt(durees.length)]);
    film.setPhoto(titreFilm.replaceAll(" ","")+".jpg");
    film.setCategorie(categories.get(new Random().nextInt(categories.size())));
    filmRepository.save(film);
});
    }



   @Override
    public void initProjection() {
        double[]prices=new double[]{30,40,50,60,75,100,150,200};
        List<Film> films=filmRepository.findAll();
        villeRepository.findAll().forEach(ville -> {
            ville.getCinemas().forEach(cinema -> {
                cinema.getSalles().forEach(salle -> {
                    int index=new Random().nextInt(films.size());
                    Film film=films.get(index);
                    seanceRepository.findAll().forEach(seance -> {
                   Projection projection=new Projection();
                   projection.setDateProjection(new Date());
                   projection.setFilm(film);
                   projection.setPrix(prices[new Random().nextInt(prices.length)]);
                   projection.setSalle(salle);
                   projection.setSeance(seance);
                   projectionRepository.save(projection);

               });
           });
        });
    });

   }


    @Override
    public void initTicket() {
        projectionRepository.findAll().forEach(projection ->{
            projection.getSalle().getPlaces().forEach(place -> {
                Ticket ticket=new Ticket();
                ticket.setPlace(place);
                ticket.setProjection(projection);
                ticket.setPrix(projection.getPrix());
                ticket.setReserve(false);
                ticketRepository.save(ticket);
            });
        } );


    }
}
