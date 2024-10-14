package me.spring.controller.dto;

public record MovimentacaoDto(
        String descricao,
        Double valor,
        Long destinatarioId
        ) {

    public MovimentacaoDto(String descricao, Double valor, Long destinatarioId) {
        this.descricao = descricao;
        this.valor = valor;
        this.destinatarioId = destinatarioId;
    }

    // public MovimentacaoDto toModel() {
    //     MovimentacaoDto model = new MovimentacaoDto();
    //     model.setId(this.id);
    //     model.setDescricao(this.descricao);
    //     model.setValor(this.valor);
    //     model.setDestinatarioId(this.destinatarioId);
    //     model.setRemetenteId(this.remetenteId);
    //     model.setCreated(this.created);
    //     return model;
    // }


}
