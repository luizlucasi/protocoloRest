package com.telespazio.domino.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.telespazio.domino.web.rest.TestUtil;

public class ProtocoloTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Protocolo.class);
        Protocolo protocolo1 = new Protocolo();
        protocolo1.setId(1L);
        Protocolo protocolo2 = new Protocolo();
        protocolo2.setId(protocolo1.getId());
        assertThat(protocolo1).isEqualTo(protocolo2);
        protocolo2.setId(2L);
        assertThat(protocolo1).isNotEqualTo(protocolo2);
        protocolo1.setId(null);
        assertThat(protocolo1).isNotEqualTo(protocolo2);
    }
}
