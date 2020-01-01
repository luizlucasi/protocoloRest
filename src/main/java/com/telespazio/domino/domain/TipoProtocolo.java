package com.telespazio.domino.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A TipoProtocolo.
 */
@Entity
@Table(name = "tipo_protocolo")
public class TipoProtocolo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "nome_protocolo", nullable = false)
    private String nomeProtocolo;

    @NotNull
    @Column(name = "cod_protocolo", nullable = false)
    private String codProtocolo;

    @NotNull
    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @OneToMany(mappedBy = "tipoProtocolo")
    private Set<Protocolo> protocolos = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeProtocolo() {
        return nomeProtocolo;
    }

    public TipoProtocolo nomeProtocolo(String nomeProtocolo) {
        this.nomeProtocolo = nomeProtocolo;
        return this;
    }

    public void setNomeProtocolo(String nomeProtocolo) {
        this.nomeProtocolo = nomeProtocolo;
    }

    public String getCodProtocolo() {
        return codProtocolo;
    }

    public TipoProtocolo codProtocolo(String codProtocolo) {
        this.codProtocolo = codProtocolo;
        return this;
    }

    public void setCodProtocolo(String codProtocolo) {
        this.codProtocolo = codProtocolo;
    }

    public Boolean isIsActive() {
        return isActive;
    }

    public TipoProtocolo isActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Set<Protocolo> getProtocolos() {
        return protocolos;
    }

    public TipoProtocolo protocolos(Set<Protocolo> protocolos) {
        this.protocolos = protocolos;
        return this;
    }

    public TipoProtocolo addProtocolo(Protocolo protocolo) {
        this.protocolos.add(protocolo);
        protocolo.setTipoProtocolo(this);
        return this;
    }

    public TipoProtocolo removeProtocolo(Protocolo protocolo) {
        this.protocolos.remove(protocolo);
        protocolo.setTipoProtocolo(null);
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
        if (!(o instanceof TipoProtocolo)) {
            return false;
        }
        return id != null && id.equals(((TipoProtocolo) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "TipoProtocolo{" +
            "id=" + getId() +
            ", nomeProtocolo='" + getNomeProtocolo() + "'" +
            ", codProtocolo='" + getCodProtocolo() + "'" +
            ", isActive='" + isIsActive() + "'" +
            "}";
    }
}
