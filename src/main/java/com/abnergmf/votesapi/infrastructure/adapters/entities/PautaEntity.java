package com.abnergmf.votesapi.infrastructure.adapters.entities;

import java.util.UUID;

import com.abnergmf.votesapi.domain.Pauta;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Pauta")
public class PautaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String nome;

    public PautaEntity() {
    }

    public PautaEntity(Pauta pauta) {
        this.id = pauta.getId();
        this.nome = pauta.getNome();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Pauta toPauta() {
        return new Pauta();
    }
    public void atualizar(Pauta pauta) {
        this.nome = pauta.getNome();
    }
}
