package com.telespazio.domino.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Numeracao.
 */
@Entity
@Table(name = "numeracao")
public class Numeracao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "numero", nullable = false)
    private Long numero;

    @NotNull
    @Column(name = "ano", nullable = false)
    private Integer ano;

    @OneToMany(mappedBy = "numeracao")
    private Set<Protocolo> protocolos = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumero() {
        return numero;
    }

    public Numeracao numero(Long numero) {
        this.numero = numero;
        return this;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public Integer getAno() {
        return ano;
    }

    public Numeracao ano(Integer ano) {
        this.ano = ano;
        return this;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Set<Protocolo> getProtocolos() {
        return protocolos;
    }

    public Numeracao protocolos(Set<Protocolo> protocolos) {
        this.protocolos = protocolos;
        return this;
    }

    public Numeracao addProtocolo(Protocolo protocolo) {
        this.protocolos.add(protocolo);
        protocolo.setNumeracao(this);
        return this;
    }

    public Numeracao removeProtocolo(Protocolo protocolo) {
        this.protocolos.remove(protocolo);
        protocolo.setNumeracao(null);
        return this;
    }

    public void setProtocolos(Set<Protocolo> protocolos) {
        this.protocolos = protocolos;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Numeracao)) {
            return false;
        }
        return id != null && id.equals(((Numeracao) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Numeracao{" +
            "id=" + getId() +
            ", numero=" + getNumero() +
            ", ano=" + getAno() +
            "}";
    }
}
