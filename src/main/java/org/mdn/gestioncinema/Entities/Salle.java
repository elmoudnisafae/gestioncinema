package org.mdn.gestioncinema.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Salle implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    private int nombrePlace;
    @ManyToOne
    @JsonBackReference
    private Cinema cinema;
    @OneToMany(mappedBy = "salle")
    @JsonBackReference
    private Collection<Place> places;
// ccchcjhshcd
    @OneToMany(mappedBy = "salle")
    @JsonBackReference
    private List<Projection> projections;
}
