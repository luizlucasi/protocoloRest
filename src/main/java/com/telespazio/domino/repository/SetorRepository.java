package com.telespazio.domino.repository;

import com.telespazio.domino.domain.Setor;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Setor entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SetorRepository extends JpaRepository<Setor, Long> {

}
