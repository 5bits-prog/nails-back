package jsges.nails.DTO.articulos;

import jsges.nails.DTO.TipoObjetoDTO;
import jsges.nails.domain.articulos.ArticuloVenta;

public class ArticuloVentaDTO extends TipoObjetoDTO {

    public Integer id;
    public String denominacion;
    public Integer linea;
    public String observacion;

    public ArticuloVentaDTO( ArticuloVenta model) {
        this.id = model.getId();
        this.denominacion=model.getDenominacion();
        this.linea=model.getLinea().getId();
        this.observacion=model.getObservacion();
    }

    public ArticuloVentaDTO( ) {

    }
}
