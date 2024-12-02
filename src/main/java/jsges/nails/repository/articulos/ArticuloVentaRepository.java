package jsges.nails.repository.articulos;

import jsges.nails.domain.articulos.ArticuloVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticuloVentaRepository extends JpaRepository<ArticuloVenta, Integer> {

    @Query("select p from ArticuloVenta p  where p.estado=0 order by p.denominacion")
    List<ArticuloVenta> buscarNoEliminados();

    @Query("SELECT a FROM ArticuloVenta a WHERE a.estado = :estado ORDER BY a.denominacion")
    List<ArticuloVenta> findByEstado(@Param("estado") Integer estado);

    @Query("SELECT p FROM ArticuloVenta p WHERE p.estado = 0 AND  p.denominacion LIKE %:consulta% ORDER BY p.denominacion")
    List<ArticuloVenta> buscarNoEliminados(@Param("consulta") String consulta);

}
