package me.spring.domain.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.spring.domain.model.enums.TipoMovimentacao;

@Entity(name = "tb_card")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Card {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String number;
    
    @Column(name = "available_limit", precision = 13, scale = 2)
    private BigDecimal limit;

    @Enumerated(EnumType.ORDINAL)
    private TipoMovimentacao tipo;



}
