package com.telespazio.domino.repository;

import com.telespazio.domino.domain.TipoProtocolo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TipoProtocolo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TipoProtocoloRepository extends JpaRepository<TipoProtocolo, Long> {

}
