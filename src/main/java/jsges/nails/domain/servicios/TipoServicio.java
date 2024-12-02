package jsges.nails.domain.servicios;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tipo_servicio")
@Data
public class TipoServicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(columnDefinition = "TEXT", name = "denominacion")
    private String denominacion;

    @Column(name = "estado")
    private Integer estado;

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
