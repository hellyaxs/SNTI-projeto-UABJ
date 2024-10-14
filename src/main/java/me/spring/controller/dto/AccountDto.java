package me.spring.controller.dto;

import me.spring.domain.model.Conta;

import java.math.BigDecimal;

public record AccountDto(Long id, String number, String agency, BigDecimal balance, BigDecimal limit) {

    public AccountDto(Conta model) {
        this(model.getId(), model.getNumber(), model.getAgency(), model.getSaldo(), model.getLimite());
    }

    public Conta toModel() {
        Conta model = new Conta();
        model.setId(this.id);
        model.setNumber(this.number);
        model.setAgency(this.agency);
        model.setSaldo(this.balance);
        model.setLimite(this.limit);
        return model;
    }
}
