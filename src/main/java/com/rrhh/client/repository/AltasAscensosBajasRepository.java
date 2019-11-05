package com.rrhh.client.repository;
import com.rrhh.client.domain.AltasAscensosBajas;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AltasAscensosBajas entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AltasAscensosBajasRepository extends JpaRepository<AltasAscensosBajas, Long>, JpaSpecificationExecutor<AltasAscensosBajas> {

}
