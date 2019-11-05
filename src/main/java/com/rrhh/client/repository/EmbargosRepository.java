package com.rrhh.client.repository;
import com.rrhh.client.domain.Embargos;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Embargos entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EmbargosRepository extends JpaRepository<Embargos, Long>, JpaSpecificationExecutor<Embargos> {

}
