package com.telespazio.domino.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Categoria.
 */
@Entity
@Table(name = "categoria")
public class Categoria implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "nome_categoria", nullable = false)
    private String nomeCategoria;

    @NotNull
    @Column(name = "cod_categoria", nullable = false)
    private String codCategoria;

    @NotNull
    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @OneToMany(mappedBy = "categoria")
    private Set<Protocolo> protocolos = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public Categoria nomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
        return this;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public String getCodCategoria() {
        return codCategoria;
    }

    public Categoria codCategoria(String codCategoria) {
        this.codCategoria = codCategoria;
        return this;
    }

    public void setCodCategoria(String codCategoria) {
        this.codCategoria = codCategoria;
    }

    public Boolean isIsActive() {
        return isActive;
    }

    public Categoria isActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Set<Protocolo> getProtocolos() {
        return protocolos;
    }

    public Categoria protocolos(Set<Protocolo> protocolos) {
        this.protocolos = protocolos;
        return this;
    }

    public Categoria addProtocolo(Protocolo protocolo) {
        this.protocolos.add(protocolo);
        protocolo.setCategoria(this);
        return this;
    }

    public Categoria removeProtocolo(Protocolo protocolo) {
        this.protocolos.remove(protocolo);
        protocolo.setCategoria(null);
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
        if (!(o instanceof Categoria)) {
            return false;
        }
        return id != null && id.equals(((Categoria) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Categoria{" +
            "id=" + getId() +
            ", nomeCategoria='" + getNomeCategoria() + "'" +
            ", codCategoria='" + getCodCategoria() + "'" +
            ", isActive='" + isIsActive() + "'" +
            "}";
    }
}
