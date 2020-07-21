package org.mdn.gestioncinema.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Ticket implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomClient;
    private double prix;
    @Column(unique = false,nullable =true)
    //int==> valeur par default est 0 ,Integer ==>valeur par def est null
    private Integer codePayment;
    private Boolean reserve;
    @ManyToOne
    @JsonBackReference
    private Place place;
    @ManyToOne
    @JsonBackReference
    private Projection projection;
}

