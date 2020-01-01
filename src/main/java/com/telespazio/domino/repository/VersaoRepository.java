package com.telespazio.domino.repository;

import com.telespazio.domino.domain.Versao;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Versao entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VersaoRepository extends JpaRepository<Versao, Long> {

}
