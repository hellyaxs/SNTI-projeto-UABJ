package me.spring.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import me.spring.domain.model.Conta;

public interface ContaRepository extends JpaRepository<Conta,Long> {

    boolean existsByNumber(String number);

    Conta findByNumber(String numeroConta);

}
