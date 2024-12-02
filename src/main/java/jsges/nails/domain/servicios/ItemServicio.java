package jsges.nails.domain.servicios;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "item_servicio")
@Data
public class ItemServicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "estado")
    private Integer estado;

    @Column(columnDefinition = "TEXT", name = "observacion")
    private String observacion;

    @ManyToOne()
    @JoinColumn (name = "tipo_servicio_id")
    private TipoServicio tipoServicio;

    @ManyToOne()
    @JoinColumn (name = "servicio_id")
    private Servicio servicio;

    @Column(name = "precio")
    private Double precio;

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
