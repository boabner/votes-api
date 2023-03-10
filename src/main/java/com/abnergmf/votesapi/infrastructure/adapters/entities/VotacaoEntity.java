package com.abnergmf.votesapi.infrastructure.adapters.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Votacao")
public class VotacaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String escolha;
    private Long associadoId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sessao_id")
    private SessaoEntity sessaoEntity;

    public VotacaoEntity() {

    }

    public VotacaoEntity(Long id, String escolha, SessaoEntity sessaoEntity, Long associadoId) {
        this.id = id;
        this.escolha = escolha;
        this.sessaoEntity = sessaoEntity;
        this.associadoId = associadoId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEscolha() {
        return escolha;
    }

    public SessaoEntity getSessaoEntity() {
        return sessaoEntity;
    }

    public Long getAssociadoId() {
        return associadoId;
    }

}
