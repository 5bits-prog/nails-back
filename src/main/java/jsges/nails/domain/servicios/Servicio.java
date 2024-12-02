package jsges.nails.domain.servicios;

import jakarta.persistence.*;
import jsges.nails.domain.organizacion.Cliente;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "servicios")
@Data
public class Servicio {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Integer id;

        @Column(name = "estado")
        private Integer estado;

        @ManyToOne()
        @JoinColumn(name = "cliente_id")
        private Cliente cliente;

        @Column(name = "fecha_registro")
        private LocalDateTime fechaRegistro;

        @Column(name = "fecha_realizacion")
        private Date fechaRealizacion;

        @Column(name = "total")
        private double total;

        @PrePersist
        protected void onCreate() {
                this.estado = 0;
                this.fechaRegistro = LocalDateTime.now();
        }

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
