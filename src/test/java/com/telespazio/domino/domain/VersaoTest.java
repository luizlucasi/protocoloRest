package com.telespazio.domino.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.telespazio.domino.web.rest.TestUtil;

public class VersaoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Versao.class);
        Versao versao1 = new Versao();
        versao1.setId(1L);
        Versao versao2 = new Versao();
        versao2.setId(versao1.getId());
        assertThat(versao1).isEqualTo(versao2);
        versao2.setId(2L);
        assertThat(versao1).isNotEqualTo(versao2);
        versao1.setId(null);
        assertThat(versao1).isNotEqualTo(versao2);
    }
}
