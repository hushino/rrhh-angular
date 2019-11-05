package com.rrhh.client.repository;
import com.rrhh.client.domain.ConceptoConocimientosEspecialesClasificacionPremios;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ConceptoConocimientosEspecialesClasificacionPremios entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ConceptoConocimientosEspecialesClasificacionPremiosRepository extends JpaRepository<ConceptoConocimientosEspecialesClasificacionPremios, Long>, JpaSpecificationExecutor<ConceptoConocimientosEspecialesClasificacionPremios> {

}
