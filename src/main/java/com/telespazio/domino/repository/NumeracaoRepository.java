package com.telespazio.domino.repository;

import com.telespazio.domino.domain.Numeracao;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Numeracao entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NumeracaoRepository extends JpaRepository<Numeracao, Long> {

}
