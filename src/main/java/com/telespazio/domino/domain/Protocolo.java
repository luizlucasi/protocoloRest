package com.telespazio.domino.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A Protocolo.
 */
@Entity
@Table(name = "protocolo")
public class Protocolo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "solicitante", nullable = false)
    private String solicitante;

    @NotNull
    @Column(name = "data_solicitacao", nullable = false)
    private Instant dataSolicitacao;

    @Column(name = "data_envio")
    private Instant dataEnvio;

    @Column(name = "enviado_por")
    private String enviadoPor;

    @NotNull
    @Column(name = "data_criacao", nullable = false)
    private Instant dataCriacao;

    @NotNull
    @Column(name = "usuario_criacao", nullable = false)
    private Instant usuarioCriacao;

    @Column(name = "localizacao")
    private String localizacao;

    @Column(name = "observacao")
    private String observacao;

    @Column(name = "nomenclatura")
    private String nomenclatura;

    @Column(name = "formato")
    private String formato;

    @ManyToOne
    @JsonIgnoreProperties("protocolos")
    private Versao versao;

    @ManyToOne
    @JsonIgnoreProperties("protocolos")
    private Documento documento;

    @ManyToOne
    @JsonIgnoreProperties("protocolos")
    private TipoProtocolo tipoProtocolo;

    @ManyToOne
    @JsonIgnoreProperties("protocolos")
    private Setor setor;

    @ManyToOne
    @JsonIgnoreProperties("protocolos")
    private Categoria categoria;

    @ManyToOne
    @JsonIgnoreProperties("protocolos")
    private Numeracao numeracao;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSolicitante() {
        return solicitante;
    }

    public Protocolo solicitante(String solicitante) {
        this.solicitante = solicitante;
        return this;
    }

    public void setSolicitante(String solicitante) {
        this.solicitante = solicitante;
    }

    public Instant getDataSolicitacao() {
        return dataSolicitacao;
    }

    public Protocolo dataSolicitacao(Instant dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
        return this;
    }

    public void setDataSolicitacao(Instant dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public Instant getDataEnvio() {
        return dataEnvio;
    }

    public Protocolo dataEnvio(Instant dataEnvio) {
        this.dataEnvio = dataEnvio;
        return this;
    }

    public void setDataEnvio(Instant dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    public String getEnviadoPor() {
        return enviadoPor;
    }

    public Protocolo enviadoPor(String enviadoPor) {
        this.enviadoPor = enviadoPor;
        return this;
    }

    public void setEnviadoPor(String enviadoPor) {
        this.enviadoPor = enviadoPor;
    }

    public Instant getDataCriacao() {
        return dataCriacao;
    }

    public Protocolo dataCriacao(Instant dataCriacao) {
        this.dataCriacao = dataCriacao;
        return this;
    }

    public void setDataCriacao(Instant dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Instant getUsuarioCriacao() {
        return usuarioCriacao;
    }

    public Protocolo usuarioCriacao(Instant usuarioCriacao) {
        this.usuarioCriacao = usuarioCriacao;
        return this;
    }

    public void setUsuarioCriacao(Instant usuarioCriacao) {
        this.usuarioCriacao = usuarioCriacao;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public Protocolo localizacao(String localizacao) {
        this.localizacao = localizacao;
        return this;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getObservacao() {
        return observacao;
    }

    public Protocolo observacao(String observacao) {
        this.observacao = observacao;
        return this;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getNomenclatura() {
        return nomenclatura;
    }

    public Protocolo nomenclatura(String nomenclatura) {
        this.nomenclatura = nomenclatura;
        return this;
    }

    public void setNomenclatura(String nomenclatura) {
        this.nomenclatura = nomenclatura;
    }

    public String getFormato() {
        return formato;
    }

    public Protocolo formato(String formato) {
        this.formato = formato;
        return this;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public Versao getVersao() {
        return versao;
    }

    public Protocolo versao(Versao versao) {
        this.versao = versao;
        return this;
    }

    public void setVersao(Versao versao) {
        this.versao = versao;
    }

    public Documento getDocumento() {
        return documento;
    }

    public Protocolo documento(Documento documento) {
        this.documento = documento;
        return this;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public TipoProtocolo getTipoProtocolo() {
        return tipoProtocolo;
    }

    public Protocolo tipoProtocolo(TipoProtocolo tipoProtocolo) {
        this.tipoProtocolo = tipoProtocolo;
        return this;
    }

    public void setTipoProtocolo(TipoProtocolo tipoProtocolo) {
        this.tipoProtocolo = tipoProtocolo;
    }

    public Setor getSetor() {
        return setor;
    }

    public Protocolo setor(Setor setor) {
        this.setor = setor;
        return this;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Protocolo categoria(Categoria categoria) {
        this.categoria = categoria;
        return this;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Numeracao getNumeracao() {
        return numeracao;
    }

    public Protocolo numeracao(Numeracao numeracao) {
        this.numeracao = numeracao;
        return this;
    }

    public void setNumeracao(Numeracao numeracao) {
        this.numeracao = numeracao;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Protocolo)) {
            return false;
        }
        return id != null && id.equals(((Protocolo) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Protocolo{" +
            "id=" + getId() +
            ", solicitante='" + getSolicitante() + "'" +
            ", dataSolicitacao='" + getDataSolicitacao() + "'" +
            ", dataEnvio='" + getDataEnvio() + "'" +
            ", enviadoPor='" + getEnviadoPor() + "'" +
            ", dataCriacao='" + getDataCriacao() + "'" +
            ", usuarioCriacao='" + getUsuarioCriacao() + "'" +
            ", localizacao='" + getLocalizacao() + "'" +
            ", observacao='" + getObservacao() + "'" +
            ", nomenclatura='" + getNomenclatura() + "'" +
            ", formato='" + getFormato() + "'" +
            "}";
    }
}
