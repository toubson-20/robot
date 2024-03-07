package transport.test.simulationRobot.model;

import java.io.Serializable;
import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "logement")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Logement implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String type;

    private String ville;

    private String adresse;

    @Column(name = "nombre_piece")
    private int nombrePiece;

    private double surface;

    private double prix;

    @Column(name = "date_construction")
    private Date dateConstruction;
}
