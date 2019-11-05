package com.rrhh.client.repository;
import com.rrhh.client.domain.OtrosServiciosPrestados;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the OtrosServiciosPrestados entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OtrosServiciosPrestadosRepository extends JpaRepository<OtrosServiciosPrestados, Long>, JpaSpecificationExecutor<OtrosServiciosPrestados> {

}
