package jsges.nails.domain.organizacion;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "cliente")
@Data
public class Cliente {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Integer id;

        @Column(columnDefinition = "TEXT", name = "razonSocial")
        private String razonSocial;

        @Column(name = "estado")
        private int estado;

        @Column(columnDefinition = "TEXT", name = "letra")
        private String letra;

        @Column(columnDefinition = "TEXT", name = "celular")
        private String celular;

        @Column(columnDefinition = "TEXT", name = "mail")
        private String mail;

        @Column(name = "fecha_inicio")
        private LocalDate fechaInicio;

        @Column(name = "fecha_nacimiento")
        private LocalDate fechaNacimiento;

        @PrePersist
        protected void onCreate() {
                this.fechaInicio = LocalDate.now();
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
