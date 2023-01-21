package com.abnergmf.votesapi.infrastructure.adapters.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.abnergmf.votesapi.domain.Sessao;

@Entity
@Table(name = "Sessao")
public class SessaoEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date dataCriacao;
    private Date dataEncerramento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pauta_id")
    private PautaEntity pautaEntity;

    public SessaoEntity() {

    }

    public SessaoEntity(Date dataCriacao, Date dataEncerramento, PautaEntity pautaEntity) {
        this.dataCriacao = dataCriacao;
        this.dataEncerramento = dataEncerramento;
        this.pautaEntity = pautaEntity;
    }

    public SessaoEntity(Long id, Date dataCriacao, Date dataEncerramento, PautaEntity pautaEntity) {
        this.id = id;
        this.dataCriacao = dataCriacao;
        this.dataEncerramento = dataEncerramento;
        this.pautaEntity = pautaEntity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public Date getDataEncerramento() {
        return dataEncerramento;
    }

    public PautaEntity getPautaEntity() {
        return pautaEntity;
    }
    public void atualizar(Sessao sessao) {
        this.dataEncerramento = sessao.getDataEncerramento();
    }
}
