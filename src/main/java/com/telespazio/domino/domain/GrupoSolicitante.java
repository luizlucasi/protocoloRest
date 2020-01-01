package com.telespazio.domino.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A GrupoSolicitante.
 */
@Entity
@Table(name = "grupo_solicitante")
public class GrupoSolicitante implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "nome_grupo_solicitante", nullable = false)
    private String nomeGrupoSolicitante;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeGrupoSolicitante() {
        return nomeGrupoSolicitante;
    }

    public GrupoSolicitante nomeGrupoSolicitante(String nomeGrupoSolicitante) {
        this.nomeGrupoSolicitante = nomeGrupoSolicitante;
        return this;
    }

    public void setNomeGrupoSolicitante(String nomeGrupoSolicitante) {
        this.nomeGrupoSolicitante = nomeGrupoSolicitante;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GrupoSolicitante)) {
            return false;
        }
        return id != null && id.equals(((GrupoSolicitante) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "GrupoSolicitante{" +
            "id=" + getId() +
            ", nomeGrupoSolicitante='" + getNomeGrupoSolicitante() + "'" +
            "}";
    }
}
