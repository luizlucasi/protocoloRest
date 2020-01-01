package com.telespazio.domino.repository;

import com.telespazio.domino.domain.GrupoSolicitante;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the GrupoSolicitante entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GrupoSolicitanteRepository extends JpaRepository<GrupoSolicitante, Long> {

}
