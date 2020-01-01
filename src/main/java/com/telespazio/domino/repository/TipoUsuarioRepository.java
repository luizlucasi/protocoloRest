package com.telespazio.domino.repository;

import com.telespazio.domino.domain.TipoUsuario;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TipoUsuario entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TipoUsuarioRepository extends JpaRepository<TipoUsuario, Long> {

}
