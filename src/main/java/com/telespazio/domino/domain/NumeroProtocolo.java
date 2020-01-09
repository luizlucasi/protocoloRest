package com.telespazio.domino.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A NumeroProtocolo.
 */
@Entity
@Table(name = "numero_protocolo")
public class NumeroProtocolo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "ano", nullable = false)
    private String ano;

    @NotNull
    @Column(name = "numero", nullable = false)
    private Long numero;

    @OneToOne
    @JoinColumn(unique = true)
    private Protocolo protocolo;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAno() {
        return ano;
    }

    public NumeroProtocolo ano(String ano) {
        this.ano = ano;
        return this;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public Long getNumero() {
        return numero;
    }

    public NumeroProtocolo numero(Long numero) {
        this.numero = numero;
        return this;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public Protocolo getProtocolo() {
        return protocolo;
    }

    public NumeroProtocolo protocolo(Protocolo protocolo) {
        this.protocolo = protocolo;
        return this;
    }

    public void setProtocolo(Protocolo protocolo) {
        this.protocolo = protocolo;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NumeroProtocolo)) {
            return false;
        }
        return id != null && id.equals(((NumeroProtocolo) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "NumeroProtocolo{" +
            "id=" + getId() +
            ", ano='" + getAno() + "'" +
            ", numero=" + getNumero() +
            "}";
    }
}
