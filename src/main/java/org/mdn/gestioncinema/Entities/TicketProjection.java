package org.mdn.gestioncinema.Entities;
import org.springframework.data.rest.core.config.Projection;
import org.springframework.web.bind.annotation.CrossOrigin;

@Projection(name="ticketproj", types= {Ticket.class})
@CrossOrigin("*")
public interface TicketProjection {
    public Long getId();
    public String getNomClient();
    public double getPrix();
    public Integer getCodePayment();
    public boolean getReserve();
    public Place getPlace();

}
