package jsges.nails.domain.articulos;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "articulo_venta")
@Data
public class ArticuloVenta {
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

        @ManyToOne()
        @JoinColumn(name = "linea_id")
        private Linea linea;


        @PrePersist
        protected void onCreate() {
                this.estado = 0;
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

