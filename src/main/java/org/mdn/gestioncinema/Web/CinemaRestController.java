package org.mdn.gestioncinema.Web;

import lombok.Data;
import org.mdn.gestioncinema.Entities.Film;
import org.mdn.gestioncinema.Entities.Ticket;
import org.mdn.gestioncinema.dao.FilmRepository;
import org.mdn.gestioncinema.dao.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class CinemaRestController {
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private TicketRepository ticketRepository;


    @GetMapping(path="imageFilm/{id}", produces = MediaType.IMAGE_JPEG_VALUE)

    public byte[] image(@PathVariable (name = "id")Long id)throws Exception{
    Film f=filmRepository.findById(id).get();
    String photoName=f.getPhoto();


    File file = new File(System.getProperty("user.home")+"/cinema/images/"+photoName);
    Path path= Paths.get(file.toURI());
    return Files.readAllBytes(path);
}

    @PostMapping("/payerTickets")
    @Transactional
    public @ResponseBody List<Ticket> payerTickets(@RequestBody TicketForm ticketForm){
    List<Ticket> listTickets = new ArrayList<>();
    ticketForm.getTickets().forEach(idTicket->{
        Ticket ticket = ticketRepository.findById(idTicket).get();
        ticket.setNomClient(ticketForm.getNomClient());
        ticket.setCodePayment(ticketForm.getCodePaiement());
        ticket.setReserve(true);
        ticketRepository.save(ticket);
        listTickets.add(ticket);
    });
    return listTickets;
}
}
@Data
class TicketForm{
    private String nomClient;
    private int codePaiement;
    private List<Long> tickets = new ArrayList<>();
}

