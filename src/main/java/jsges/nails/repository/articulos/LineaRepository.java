package jsges.nails.repository.articulos;

import jsges.nails.domain.articulos.Linea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LineaRepository extends JpaRepository<Linea, Integer> {

    @Query("select p from Linea p  where p.estado=0 order by p.denominacion")
    List<Linea> buscarNoEliminados();

    Optional<Linea> findById(Integer id);


    @Query("SELECT p FROM Linea p WHERE p.estado = 0 AND  p.denominacion LIKE %:consulta% ORDER BY p.denominacion")
    List<Linea> buscarNoEliminados(@Param("consulta") String consulta);

    @Query("SELECT p FROM Linea p WHERE p.estado = 0 AND p.denominacion = :consulta ORDER BY p.denominacion")
    List<Linea> buscarExacto(@Param("consulta") String consulta);

    List<Linea> findByDenominacionContaining(String consulta);
}
