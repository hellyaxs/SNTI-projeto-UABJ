package me.spring.domain.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "tb_movimentacao")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Movimentacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    @Column
    private Double valor;


    @ManyToOne
    @JoinColumn(name = "destinatario_id")
    private Conta destinatario;


    @ManyToOne
    @JoinColumn(name = "remetente_id")
    private Conta remetente;


    @Column(name = "data_movimentacao")
    private LocalDateTime created;

}
