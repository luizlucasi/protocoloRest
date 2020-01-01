package com.telespazio.domino.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Versao.
 */
@Entity
@Table(name = "versao")
public class Versao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "numero_versao", nullable = false)
    private String numeroVersao;

    @OneToMany(mappedBy = "versao")
    private Set<Protocolo> protocolos = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroVersao() {
        return numeroVersao;
    }

    public Versao numeroVersao(String numeroVersao) {
        this.numeroVersao = numeroVersao;
        return this;
    }

    public void setNumeroVersao(String numeroVersao) {
        this.numeroVersao = numeroVersao;
    }

    public Set<Protocolo> getProtocolos() {
        return protocolos;
    }

    public Versao protocolos(Set<Protocolo> protocolos) {
        this.protocolos = protocolos;
        return this;
    }

    public Versao addProtocolo(Protocolo protocolo) {
        this.protocolos.add(protocolo);
        protocolo.setVersao(this);
        return this;
    }

    public Versao removeProtocolo(Protocolo protocolo) {
        this.protocolos.remove(protocolo);
        protocolo.setVersao(null);
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
        if (!(o instanceof Versao)) {
            return false;
        }
        return id != null && id.equals(((Versao) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Versao{" +
            "id=" + getId() +
            ", numeroVersao='" + getNumeroVersao() + "'" +
            "}";
    }
}
