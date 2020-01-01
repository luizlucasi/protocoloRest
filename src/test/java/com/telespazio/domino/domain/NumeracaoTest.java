package com.telespazio.domino.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.telespazio.domino.web.rest.TestUtil;

public class NumeracaoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Numeracao.class);
        Numeracao numeracao1 = new Numeracao();
        numeracao1.setId(1L);
        Numeracao numeracao2 = new Numeracao();
        numeracao2.setId(numeracao1.getId());
        assertThat(numeracao1).isEqualTo(numeracao2);
        numeracao2.setId(2L);
        assertThat(numeracao1).isNotEqualTo(numeracao2);
        numeracao1.setId(null);
        assertThat(numeracao1).isNotEqualTo(numeracao2);
    }
}
