package br.univille.homeservice.model;

public enum StatusOrcamento {
    PENDENTE("Pendente"), 
    AGUARDANDO_APROVACAO("Aguardando Aprovação"), 
    APROVADO("Aprovado");

    private String status;

    private StatusOrcamento(String status) {
        this.status = status;
    }
    public String getDescricao() {
        return status;
    }
}
