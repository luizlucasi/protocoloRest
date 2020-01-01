package com.telespazio.domino.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.telespazio.domino.web.rest.TestUtil;

public class GrupoSolicitanteTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GrupoSolicitante.class);
        GrupoSolicitante grupoSolicitante1 = new GrupoSolicitante();
        grupoSolicitante1.setId(1L);
        GrupoSolicitante grupoSolicitante2 = new GrupoSolicitante();
        grupoSolicitante2.setId(grupoSolicitante1.getId());
        assertThat(grupoSolicitante1).isEqualTo(grupoSolicitante2);
        grupoSolicitante2.setId(2L);
        assertThat(grupoSolicitante1).isNotEqualTo(grupoSolicitante2);
        grupoSolicitante1.setId(null);
        assertThat(grupoSolicitante1).isNotEqualTo(grupoSolicitante2);
    }
}
