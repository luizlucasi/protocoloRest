package com.telespazio.domino.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.telespazio.domino.web.rest.TestUtil;

public class TipoProtocoloTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TipoProtocolo.class);
        TipoProtocolo tipoProtocolo1 = new TipoProtocolo();
        tipoProtocolo1.setId(1L);
        TipoProtocolo tipoProtocolo2 = new TipoProtocolo();
        tipoProtocolo2.setId(tipoProtocolo1.getId());
        assertThat(tipoProtocolo1).isEqualTo(tipoProtocolo2);
        tipoProtocolo2.setId(2L);
        assertThat(tipoProtocolo1).isNotEqualTo(tipoProtocolo2);
        tipoProtocolo1.setId(null);
        assertThat(tipoProtocolo1).isNotEqualTo(tipoProtocolo2);
    }
}
