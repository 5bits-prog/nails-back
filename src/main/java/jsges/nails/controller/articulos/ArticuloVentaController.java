package jsges.nails.controller.articulos;

import jsges.nails.DTO.articulos.ArticuloVentaDTO;
import jsges.nails.service.articulos.IArticuloVentaService;
import jsges.nails.service.articulos.ILineaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping(value="${path_mapping}")
@CrossOrigin(value="${path_cross}")
public class ArticuloVentaController {
    private final IArticuloVentaService modelService;
    private final ILineaService lineaService;

    @Autowired
    public ArticuloVentaController(IArticuloVentaService modelService, ILineaService lineaService) {
        this.modelService = modelService;
        this.lineaService = lineaService;
    }

    @GetMapping({"/articulos"})
    public List<ArticuloVentaDTO> getAll() {
        /*
        * Lleve la logica de pasar la lista de models a dtos al services
        * */
        return modelService.listar();
    }

    @GetMapping({"/articulosPageQuery"})
    public ResponseEntity<Page<ArticuloVentaDTO>> getItems(@RequestParam(defaultValue = "") String consulta, @RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "${max_page}") int size) {
        List<ArticuloVentaDTO> listado = modelService.listar(consulta);
        Page<ArticuloVentaDTO> bookPage = modelService.findPaginated(PageRequest.of(page, size),listado);
        return ResponseEntity.ok().body(bookPage);
    }

    @GetMapping("/articulos/{id}")
    public ResponseEntity<ArticuloVentaDTO> getPorId(@PathVariable Integer id){
        ArticuloVentaDTO modelDTO = new ArticuloVentaDTO(modelService.buscarPorId(id));
        return ResponseEntity.ok(modelDTO);
    }

    @PostMapping("/articulos")
    public ArticuloVentaDTO agregar(@RequestBody ArticuloVentaDTO model){
        return modelService.guardar(model);
    }


    @DeleteMapping("/articuloEliminar/{id}")
    public ResponseEntity<ArticuloVentaDTO> eliminar(@PathVariable Integer id){
        return ResponseEntity.ok().body(modelService.eliminar(id));
    }

    @PutMapping("/articulos/{id}")
    public ResponseEntity<ArticuloVentaDTO> actualizar(@PathVariable Integer id,
                                                    @RequestBody ArticuloVentaDTO modelRecibido){
        return ResponseEntity.ok(modelService.actualizar(id,modelRecibido));
    }

}

