package com.rrhh.client.repository;
import com.rrhh.client.domain.PenasDisciplinariasSufridas;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PenasDisciplinariasSufridas entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PenasDisciplinariasSufridasRepository extends JpaRepository<PenasDisciplinariasSufridas, Long>, JpaSpecificationExecutor<PenasDisciplinariasSufridas> {

}
