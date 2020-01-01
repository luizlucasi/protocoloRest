package com.telespazio.domino.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Documento.
 */
@Entity
@Table(name = "documento")
public class Documento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "nome_documento", nullable = false)
    private String nomeDocumento;

    @NotNull
    @Column(name = "cod_documento", nullable = false)
    private String codDocumento;

    @NotNull
    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @OneToMany(mappedBy = "documento")
    private Set<Protocolo> protocolos = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeDocumento() {
        return nomeDocumento;
    }

    public Documento nomeDocumento(String nomeDocumento) {
        this.nomeDocumento = nomeDocumento;
        return this;
    }

    public void setNomeDocumento(String nomeDocumento) {
        this.nomeDocumento = nomeDocumento;
    }

    public String getCodDocumento() {
        return codDocumento;
    }

    public Documento codDocumento(String codDocumento) {
        this.codDocumento = codDocumento;
        return this;
    }

    public void setCodDocumento(String codDocumento) {
        this.codDocumento = codDocumento;
    }

    public Boolean isIsActive() {
        return isActive;
    }

    public Documento isActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Set<Protocolo> getProtocolos() {
        return protocolos;
    }

    public Documento protocolos(Set<Protocolo> protocolos) {
        this.protocolos = protocolos;
        return this;
    }

    public Documento addProtocolo(Protocolo protocolo) {
        this.protocolos.add(protocolo);
        protocolo.setDocumento(this);
        return this;
    }

    public Documento removeProtocolo(Protocolo protocolo) {
        this.protocolos.remove(protocolo);
        protocolo.setDocumento(null);
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
        if (!(o instanceof Documento)) {
            return false;
        }
        return id != null && id.equals(((Documento) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Documento{" +
            "id=" + getId() +
            ", nomeDocumento='" + getNomeDocumento() + "'" +
            ", codDocumento='" + getCodDocumento() + "'" +
            ", isActive='" + isIsActive() + "'" +
            "}";
    }
}
