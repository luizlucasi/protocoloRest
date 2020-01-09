package com.telespazio.domino.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.telespazio.domino.web.rest.TestUtil;

public class NumeroProtocoloTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NumeroProtocolo.class);
        NumeroProtocolo numeroProtocolo1 = new NumeroProtocolo();
        numeroProtocolo1.setId(1L);
        NumeroProtocolo numeroProtocolo2 = new NumeroProtocolo();
        numeroProtocolo2.setId(numeroProtocolo1.getId());
        assertThat(numeroProtocolo1).isEqualTo(numeroProtocolo2);
        numeroProtocolo2.setId(2L);
        assertThat(numeroProtocolo1).isNotEqualTo(numeroProtocolo2);
        numeroProtocolo1.setId(null);
        assertThat(numeroProtocolo1).isNotEqualTo(numeroProtocolo2);
    }
}
