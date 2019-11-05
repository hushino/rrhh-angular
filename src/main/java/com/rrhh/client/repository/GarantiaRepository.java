package com.rrhh.client.repository;
import com.rrhh.client.domain.Garantia;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Garantia entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GarantiaRepository extends JpaRepository<Garantia, Long>, JpaSpecificationExecutor<Garantia> {

}
