package jsges.nails.DTO.articulos;

import jsges.nails.DTO.TipoObjetoDTO;
import jsges.nails.domain.articulos.Linea;
import lombok.Data;

@Data
public class LineaDTO extends TipoObjetoDTO {
    private String observacion;
    public LineaDTO() {
       super();
    }

    public LineaDTO(Linea linea) {
        this.id= linea.getId();
        this.denominacion= linea.getDenominacion();
        this.observacion= linea.getObservacion();
    }
}
