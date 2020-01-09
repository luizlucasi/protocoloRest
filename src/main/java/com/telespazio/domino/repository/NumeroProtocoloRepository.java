package com.telespazio.domino.repository;

import com.telespazio.domino.domain.NumeroProtocolo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the NumeroProtocolo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NumeroProtocoloRepository extends JpaRepository<NumeroProtocolo, Long> {

}
