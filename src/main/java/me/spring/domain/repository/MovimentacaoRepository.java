package me.spring.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.spring.domain.model.Movimentacao;

@Repository
public interface MovimentacaoRepository extends JpaRepository<Movimentacao,Long> {

}
