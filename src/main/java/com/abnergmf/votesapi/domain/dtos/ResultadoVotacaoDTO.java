package com.abnergmf.votesapi.domain.dtos;

import org.springframework.web.bind.annotation.ResponseBody;

public class ResultadoVotacaoDTO {

    private Long sessaoId;
    private String resultado;
    private Integer quantidadeVotosSim;
    private Integer quantidadeVotosNao;

    public ResultadoVotacaoDTO(Long sessaoId) {
        this.sessaoId = sessaoId;
        this.quantidadeVotosSim = 0;
        this.quantidadeVotosNao = 0;
    }

    public ResultadoVotacaoDTO(Long sessaoId, String resultado) {
        this.sessaoId = sessaoId;
        this.resultado = resultado;
    }

    public Long getSessaoId() {
        return sessaoId;
    }

    public void setSessaoId(Long sessaoId) {
        this.sessaoId = sessaoId;
    }

    public Integer getQuantidadeVotosSim() {
        return quantidadeVotosSim;
    }

    public Integer getQuantidadeVotosNao() {
        return quantidadeVotosNao;
    }

    public Integer getTotal() {
        return quantidadeVotosNao + quantidadeVotosSim;
    }

    public String getResultado() {
        return resultado;
    }

    @ResponseBody
    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public void registrarResultado(String resultado) {
        this.resultado = resultado;
    }

    public void somarVotoSim() {
        this.quantidadeVotosSim++;
    }

    public void somarVotoNao() {
        this.quantidadeVotosNao++;
    }

}