package jsges.nails.domain.articulos;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "linea")
@Data
public class Linea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(columnDefinition = "TEXT", name = "denominacion")
    private String denominacion;

    @Column(name = "estado")
    private int estado;

    @Column(columnDefinition = "TEXT", name = "observacion")
    private String observacion;

    public void eliminar () {
        this.setEstado(1);
    }

    public void recuperar () {
        this.setEstado(0);
    }

    public boolean esEliminado () {
        return this.getEstado() == 1;
    }
}

