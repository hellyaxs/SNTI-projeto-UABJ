package me.spring.controller;

import java.math.BigDecimal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import me.spring.controller.dto.movimentacoesContaDto;
import me.spring.service.impl.ContaService;

@RestController
@RequestMapping("/conta")
public class ContaController {


    private final ContaService contaService;

    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    @PostMapping("/depositar/{numeroConta}")
    public ResponseEntity<?> depositar(@PathVariable String numeroConta, @RequestBody BigDecimal saldo) {
        contaService.depositar(numeroConta, saldo);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/transferir")
    public ResponseEntity<?> transferir(@RequestBody movimentacoesContaDto conta) {
        contaService.trasfeir(conta.remetenteId(), conta.destinatarioId(), conta.valor());
        return ResponseEntity.ok().build();
    }
    
}
