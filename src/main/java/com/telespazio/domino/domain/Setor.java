package com.telespazio.domino.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Setor.
 */
@Entity
@Table(name = "setor")
public class Setor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "nome_setor", nullable = false)
    private String nomeSetor;

    @NotNull
    @Column(name = "codigo_setor", nullable = false)
    private String codigoSetor;

    @NotNull
    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @OneToMany(mappedBy = "setor")
    private Set<Protocolo> protocolos = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeSetor() {
        return nomeSetor;
    }

    public Setor nomeSetor(String nomeSetor) {
        this.nomeSetor = nomeSetor;
        return this;
    }

    public void setNomeSetor(String nomeSetor) {
        this.nomeSetor = nomeSetor;
    }

    public String getCodigoSetor() {
        return codigoSetor;
    }

    public Setor codigoSetor(String codigoSetor) {
        this.codigoSetor = codigoSetor;
        return this;
    }

    public void setCodigoSetor(String codigoSetor) {
        this.codigoSetor = codigoSetor;
    }

    public Boolean isIsActive() {
        return isActive;
    }

    public Setor isActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Set<Protocolo> getProtocolos() {
        return protocolos;
    }

    public Setor protocolos(Set<Protocolo> protocolos) {
        this.protocolos = protocolos;
        return this;
    }

    public Setor addProtocolo(Protocolo protocolo) {
        this.protocolos.add(protocolo);
        protocolo.setSetor(this);
        return this;
    }

    public Setor removeProtocolo(Protocolo protocolo) {
        this.protocolos.remove(protocolo);
        protocolo.setSetor(null);
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
        if (!(o instanceof Setor)) {
            return false;
        }
        return id != null && id.equals(((Setor) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Setor{" +
            "id=" + getId() +
            ", nomeSetor='" + getNomeSetor() + "'" +
            ", codigoSetor='" + getCodigoSetor() + "'" +
            ", isActive='" + isIsActive() + "'" +
            "}";
    }
}
