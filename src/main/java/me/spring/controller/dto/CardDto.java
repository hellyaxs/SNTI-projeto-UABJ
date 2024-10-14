package me.spring.controller.dto;

import me.spring.domain.model.Card;

import java.math.BigDecimal;

public record CardDto(String number, BigDecimal limit) {

    public CardDto(Card model) {
        this(model.getNumber(), model.getLimit());
    }

    public Card toModel() {
        Card model = new Card();
        model.setNumber(this.number);
        model.setLimit(this.limit);
        return model;
    }
}
