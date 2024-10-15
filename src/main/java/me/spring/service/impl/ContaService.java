package me.spring.service.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import me.spring.domain.model.Conta;
import me.spring.domain.repository.ContaRepository;

@Service
public class ContaService {

    private final ContaRepository contaRepository;

    public ContaService(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }


    @Transactional
    public void trasfeir(Long remetenteId, Long destinatarioId, BigDecimal valor) {
        var remtente = contaRepository.findById(remetenteId).orElseThrow();
        var destinatario = contaRepository.findById(destinatarioId).orElseThrow();

        if (remtente.getSaldo().compareTo(valor) <= 0) {
            throw new UnsupportedOperationException("Saldo insuficiente");
        }


        remtente.setSaldo(remtente.getSaldo().subtract(valor));
        destinatario.setSaldo(destinatario.getSaldo().add(valor));

        contaRepository.save(remtente);
        contaRepository.save(destinatario);
    }

    public boolean existsByContaNumber(String number) {
        return contaRepository.existsByNumber(number);
    }


    public Conta findByContaNumber(String numeroConta) {
        return contaRepository.findByNumber(numeroConta);
    }
    

}
