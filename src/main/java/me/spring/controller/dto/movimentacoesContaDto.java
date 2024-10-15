package me.spring.controller.dto;

import java.math.BigDecimal;


public record movimentacoesContaDto(String remetenteId, String destinatarioId, BigDecimal valor) {

}
