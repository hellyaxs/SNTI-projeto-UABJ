package me.spring.controller.dto;

import me.spring.domain.model.Conta;

import java.math.BigDecimal;

public record AccountDto(String number, String agency, BigDecimal balance, BigDecimal limit) {

    public AccountDto(Conta model) {
        this(model.getNumber(), model.getAgency(), model.getSaldo(), model.getLimite());
    }

    public Conta toModel() {
        Conta model = new Conta();
        model.setNumber(this.number);
        model.setAgency(this.agency);
        model.setSaldo(this.balance);
        model.setLimite(this.limit);
        return model;
    }
}
