package com.telespazio.domino.repository;

import com.telespazio.domino.domain.Protocolo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Protocolo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProtocoloRepository extends JpaRepository<Protocolo, Long> {

}
