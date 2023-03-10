package com.abnergmf.votesapi.infrastructure.adapters.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.abnergmf.votesapi.domain.Pauta;

@Entity
@Table(name = "Pauta")
public class PautaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    public PautaEntity() {

    }

    public PautaEntity(String nome) {
        this.nome = nome;
    }

    public PautaEntity(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void atualizar(Pauta pauta) {
        this.nome = pauta.getNome();
    }
}
