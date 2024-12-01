package jsges.nails.service.articulos;

import jsges.nails.DTO.articulos.ArticuloVentaDTO;
import jsges.nails.domain.articulos.ArticuloVenta;
import jsges.nails.excepcion.BadRequestException;
import jsges.nails.excepcion.RecursoNoEncontradoExcepcion;
import jsges.nails.repository.articulos.ArticuloVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Service
public class ArticuloVentaService implements IArticuloVentaService{
    private final ArticuloVentaRepository modelRepository;

    @Autowired
    public ArticuloVentaService(ArticuloVentaRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    @Override
    public List<ArticuloVentaDTO> listar() {
        List<ArticuloVenta> list = modelRepository.buscarNoEliminados();
        List<ArticuloVentaDTO> listadoDTO    =  new ArrayList<>();
        list.forEach((model) -> {
            listadoDTO.add(new ArticuloVentaDTO(model));
        });
        return listadoDTO;
    }

    @Override
    public List<ArticuloVentaDTO> listar(String consulta) {
        //Segundo cambio, verificar la existencia del parametro consulta
        if (consulta == null || consulta.trim().isEmpty()) {
            throw new BadRequestException("El parámetro consulta no puede ser nulo o vacío.");
        }
        List<ArticuloVenta> list = modelRepository.buscarNoEliminados(consulta);
        List<ArticuloVentaDTO> listadoDTO    =  new ArrayList<>();
        list.forEach((model) -> {
            listadoDTO.add(new ArticuloVentaDTO(model));
        });
        return listadoDTO;
    }

    @Override
    public ArticuloVenta buscarPorId(Integer id) {
        ArticuloVenta articuloVenta = modelRepository.findById(id).orElse(null);
        if(articuloVenta == null){
            throw new RecursoNoEncontradoExcepcion("No se encontro el id: " + id);
        }
        return articuloVenta;
    }

    @Override
    public ArticuloVentaDTO guardar(ArticuloVentaDTO modelDTO) {
        Integer idLinea = modelDTO.linea;
        ArticuloVenta newModel =  new ArticuloVenta();
        newModel.setDenominacion(modelDTO.denominacion);
        newModel.setObservacion(modelDTO.observacion);
        LineaService lineaService = new LineaService();
        newModel.setLinea(lineaService.buscarPorId(idLinea));
        modelRepository.save(newModel);
        ArticuloVentaDTO modelDTO2 = new ArticuloVentaDTO(newModel);
        return modelDTO2;
    }

    @Override
    public ArticuloVentaDTO eliminar(Integer id) {
        //Primer cambio agregar la logica para eliminar el model
        ArticuloVenta model = modelRepository.findById(id).orElse(null);
        if (model == null) {
            throw new RecursoNoEncontradoExcepcion("El id recibido no existe: " + id);
        }
        if (model.esEliminado()) {
            throw new BadRequestException("El id recibido ya se encuentra eliminado: " + id);
        }
        model.eliminar();
        modelRepository.save(model);
        return new ArticuloVentaDTO(model);
    }



    @Override
    public Page<ArticuloVenta> getArticulos(Pageable pageable) {
        return  modelRepository.findAll(pageable);
    }

    @Override
    public Page<ArticuloVentaDTO> findPaginated(Pageable pageable, List<ArticuloVentaDTO> listado) {
        try {
            int pageSize = pageable.getPageSize();
            int currentPage = pageable.getPageNumber();
            int startItem = currentPage * pageSize;
            List<ArticuloVentaDTO> list;
            if (listado.size() < startItem) {
                list = Collections.emptyList();
            } else {
                int toIndex = Math.min(startItem + pageSize, listado.size());
                list = listado.subList(startItem, toIndex);
            }

            Page<ArticuloVentaDTO> bookPage  = new PageImpl<ArticuloVentaDTO>(list, PageRequest.of(currentPage, pageSize), listado.size());

            return bookPage;
        } catch (Exception e) {
            throw new BadRequestException("Error al obtener la paginación: " + e.getMessage());
        }

    }

    @Override
    public ArticuloVentaDTO actualizar(Integer id,ArticuloVentaDTO modelDTO){
        Boolean cambios = false;
        ArticuloVenta model = modelRepository.findById(id).orElse(null);
        if (model == null) {
            throw new RecursoNoEncontradoExcepcion("El id recibido no existe: " + id);
        }
        if (model.esEliminado()) {
            throw new BadRequestException("El id recibido ya se encuentra eliminado: " + id);
        }
        if (modelDTO.linea != null && modelDTO.linea != model.getLinea().getId()) {
            LineaService lineaService = new LineaService();
            model.setLinea(lineaService.buscarPorId(modelDTO.linea));
            cambios = true;
        }
        if (modelDTO.denominacion != null && modelDTO.denominacion != model.getDenominacion()) {
            model.setDenominacion(modelDTO.denominacion);
            cambios = true;
        }
        if (modelDTO.observacion != null && modelDTO.observacion != model.getObservacion()) {
            model.setObservacion(modelDTO.observacion);
            cambios = true;
        }
        if (cambios){
            modelRepository.save(model);
            return new ArticuloVentaDTO(model);
        }else {
            throw new BadRequestException("No se realizaron cambios");
        }
    }
}
